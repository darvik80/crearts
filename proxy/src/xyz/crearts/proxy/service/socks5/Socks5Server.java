package xyz.crearts.proxy.service.socks5;

import lombok.extern.java.Log;
import xyz.crearts.proxy.service.AuthenticationService;

import javax.annotation.concurrent.GuardedBy;

import java.io.IOException;
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
@Log
public class Socks5Server implements Runnable {
    private ServerSocketChannel ssc;
    private ServerSocket ss;

    private int port;
    private String host;

    @GuardedBy("this")
    private Thread mainThread;

    private AuthenticationService authService;

    public Socks5Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socks5Server(String host, int port, AuthenticationService authService) {
        this.host = host;
        this.port = port;
        this.authService = authService;
    }

    public synchronized void start() throws IOException {
        if (mainThread != null) {
            log.warning("socks5 proxy already started");
            throw new IllegalStateException("SMTPServer can only be started once");
        }

        try {
            ssc = ServerSocketChannel.open();
            ss = ssc.socket();
            ss.bind(new InetSocketAddress(host, port));
            ssc.configureBlocking(false);
        } catch (IOException ex) {
            log.warning("Can't' open socks5 server socket: " + ex.getMessage());
            throw ex;
        }

        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void stop() {
        if (mainThread == null) {
            log.warning("socks5 proxy already stopped");
            return;
        }

        mainThread.interrupt();
        try {
            mainThread.join();
        } catch (InterruptedException ex) {
            log.warning("Can't stop socks5: " + ex.getMessage());
        }

        try {
            this.ss.close();
            this.ssc.close();
        } catch (IOException ex) {
            log.warning("Can't close socks5 socket: " + ex.getMessage());
        }

        mainThread = null;
    }

    public synchronized boolean isStarted() {
        return this.mainThread != null;
    }

    @Override
    public void run() {

        try {
            Selector s = Selector.open();
            ssc.register(s, SelectionKey.OP_ACCEPT);
            log.info("Start socks5 proxy " + this.host + ":" + this.port);

            while (!Thread.interrupted()) {
                int n = s.select(1000);
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

                            sc.register(s, SelectionKey.OP_READ, new Socks5Session(sc, s, this.authService));
                        } else if (key.isReadable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            try {
                                Socks5Session connection = (Socks5Session) key.attachment();
                                connection.onRead(sc);
                            } catch (InvalidParameterException ex) {
                                log.warning(ex.getMessage());
                                sc.close();
                            }
                        }
                    }
                    it.remove();
                }
            }
        } catch(Exception ex){
            log.warning(ex.getMessage());
        } finally {
            log.info("Stop socks5 proxy " + this.host + ":" + this.port);
        }
    }
}
