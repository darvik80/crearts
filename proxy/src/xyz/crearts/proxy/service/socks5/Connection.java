package xyz.crearts.proxy.service.socks5;

import xyz.crearts.proxy.domain.socks5.*;
import xyz.crearts.proxy.exception.IncompleteException;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * @author ivan.kishchenko
 */
public class Connection {
    enum State{
        WAIT_REQUEST,
        WAIT_AUTH,
        WAIT_COMMAND,
        WAIT_DISCONNECT
    }

    private Selector selector;
    private SocketChannel socketChannel;
    private SocketChannel originChannel;
    private ByteBuffer raw = ByteBuffer.allocate(1024);
    private State state;

    Connection(SocketChannel socketChannel, Selector selector) {
        this.socketChannel = socketChannel;
        this.selector = selector;
        this.state = State.WAIT_REQUEST;
    }

    private State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    private void cleanup() {

        try {
            if (this.originChannel != null) {
                if (this.originChannel.isConnected()) {
                    this.originChannel.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (this.socketChannel != null) {
                if (this.socketChannel.isConnected()) {
                    this.socketChannel.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void proxy(SocketChannel channel) {
        int readed = 0;
        try {
            ByteBuffer incBuffer = ByteBuffer.allocate(16384);

            while ((readed = channel.read(incBuffer)) > 0) {
                incBuffer.flip();
                while (incBuffer.hasRemaining()) {
                    if (channel == this.originChannel) {
                        this.socketChannel.write(incBuffer);
                    } else if (channel == this.socketChannel) {
                        this.originChannel.write(incBuffer);
                    }
                }
                incBuffer.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            readed = -1;
        }

        if (readed == -1) {
            cleanup();
        }
    }

    private void handshake(SocketChannel channel) {
        int readed = 0;
        try {
            ByteBuffer incBuffer = ByteBuffer.allocate(256);

            while ((readed = channel.read(incBuffer)) > 0) {
                incBuffer.flip();

                this.raw.put(incBuffer);
                InputStream src = new ByteArrayInputStream(this.raw.array());
                ByteArrayOutputStream dst = new ByteArrayOutputStream();

                switch (getState()) {
                    case WAIT_REQUEST:
                        doRequest(src, dst);
                        break;
                    case WAIT_COMMAND:
                        doCommand(src, dst);
                        break;
                }

                ByteBuffer outBuffer = ByteBuffer.wrap(dst.toByteArray());
                while (outBuffer.hasRemaining()) {
                    channel.write(outBuffer);
                }

                this.raw.clear();
            }
        } catch (IncompleteException ex) {
            System.out.println("Not enough data");
        } catch (Exception ex) {
            ex.printStackTrace();
            readed = -1;
        }

        if (readed == -1) {
            cleanup();
        }
    }

    public void onRead(SocketChannel channel) {
        if (getState() == State.WAIT_DISCONNECT) {
            proxy(channel);
        } else {
            handshake(channel);
        }
    }

    private void doRequest(InputStream is, OutputStream os) throws IOException, IncompleteException {
        HandshakeRequest req = new HandshakeRequest().read(is);

        HandshakeResponse res = new HandshakeResponse();
        if (req.getAuthList().contains(AuthType.AUTH_NONE)) {
            res.setAuthType(AuthType.AUTH_NONE);
            this.setState(State.WAIT_COMMAND);
        } else {
            res.setAuthType(AuthType.AUTH_UNSUPPORT);
            this.setState(State.WAIT_REQUEST);
        }

        os.write(res.getBytes());
    }

    private void doCommand(InputStream is, OutputStream os) throws IOException, ExecutionException, InterruptedException, IncompleteException {
        CommandRequest req = new CommandRequest().read(is);

        switch (req.getOperationType()) {
            case Connection:
                this.originChannel = SocketChannel.open();
                InetSocketAddress addr = new InetSocketAddress(req.getAddress(), req.getPort());
                this.originChannel.connect(addr);
                this.originChannel.configureBlocking(false);
                this.originChannel.register(this.selector, SelectionKey.OP_READ, this);
            default:
                break;
        }

        CommandResponse res = new CommandResponse();
        if (this.originChannel == null) {
            res.setResponseType(ResponseType.ConnectionRejected);
            this.setState(State.WAIT_REQUEST);
        } else {
            res.setResponseType(ResponseType.Ok);
            res.setAddressType(req.getAddressType());
            res.setAddress(req.getAddress());
            res.setPort(req.getPort());
            this.setState(State.WAIT_DISCONNECT);
        }

        os.write(res.getBytes());
    }
}
