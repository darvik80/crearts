package xyz.crearts.proxy.service.dns;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;

public class DNSProxyServer implements Runnable{
    private String mainHost = "127.0.0.1";
    private int mainPort = 10053;
    private String upstreamHost;
    private int upstreamPort = 53;


    static int MAX_PACKET_SIZE = 16384;

    @Override
    public void run() {
        try {
            Selector selector = Selector.open();
            DatagramChannel channel = DatagramChannel.open();
            InetSocketAddress isa = new InetSocketAddress(mainHost, mainPort);
            channel.socket().bind(isa);
            channel.configureBlocking(false);
            SelectionKey clientKey = channel.register(selector, SelectionKey.OP_READ);


            DatagramChannel upstream = DatagramChannel.open();
            InetSocketAddress upstreamAddress = new InetSocketAddress(upstreamHost, upstreamPort);

            final ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
            while (!Thread.interrupted()) {
                try {
                    selector.select();
                    Iterator selectedKeys = selector.selectedKeys().iterator();
                    while (selectedKeys.hasNext()) {
                        try {
                            SelectionKey key = (SelectionKey) selectedKeys.next();
                            selectedKeys.remove();

                            if (!key.isValid()) {
                                continue;
                            }

                            if (key.isReadable()) {
                                DatagramChannel chan = (DatagramChannel)key.channel();
                                SocketAddress addr = chan.receive(buffer);


                                key.interestOps(SelectionKey.OP_WRITE);
                            } else if (key.isWritable()) {

                                key.interestOps(SelectionKey.OP_READ);
                            }
                        } catch (IOException e) {
                            System.err.println("glitch, continuing... " +(e.getMessage()!=null?e.getMessage():""));
                        }
                    }
                } catch (IOException e) {
                    System.err.println("glitch, continuing... " +(e.getMessage()!=null?e.getMessage():""));
                }
            }
        } catch (IOException e) {
            System.err.println("network error: " + (e.getMessage()!=null?e.getMessage():""));
        }
    }

    /*
    private void read(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel)key.channel();
        Con con = (Con)key.attachment();
        con.sa = chan.receive(con.req);
        System.out.println(new String(con.req.array(), "UTF-8"));
        con.resp = Charset.forName( "UTF-8" ).newEncoder().encode(CharBuffer.wrap("send the same string"));
    }

    private void write(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel)key.channel();
        Con con = (Con)key.attachment();
        chan.send(con.resp, con.sa);
    }
    */
}
