package xyz.crearts.proxy;

import xyz.crearts.proxy.service.socks5.Server;

/**
 * @author ivan.kishchenko
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server("0.0.0.0", 9001);
        server.run();
    }
}
