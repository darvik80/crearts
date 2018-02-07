package xyz.crearts.proxy.service.socks5;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.InvalidParameterException;
import java.util.Iterator;

/**
 * @author ivan.kishchenko
 */
public class Server implements Runnable {
    private int port;
    private String host;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ServerSocket ss = ssc.socket();
            ss.bind(new InetSocketAddress(host, port));
            ssc.configureBlocking(false);
            Selector s = Selector.open();
            ssc.register(s, SelectionKey.OP_ACCEPT);

            while (true) {
                int n = s.select();
                if (n == 0) {
                    continue;
                }

                Iterator it = s.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();

                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
                            if (sc == null) {
                                continue;
                            }
                            sc.configureBlocking(false);

                            sc.register(s, SelectionKey.OP_READ, new Connection(sc, s));
                        } else if (key.isReadable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            try {
                                Connection connection = (Connection) key.attachment();
                                connection.onRead(sc);
                            } catch (InvalidParameterException ex) {
                                ex.printStackTrace();
                                sc.close();
                            }
                        }
                    }
                    it.remove();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
