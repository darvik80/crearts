package xyz.crearts.proxy.domain.socks5;

import xyz.crearts.proxy.exception.IncompleteException;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.security.InvalidParameterException;

/**
 * @author ivan.kishchenko
 */
public class Helper {
    public static final byte PROTO_SOCKS4 = 0x04;
    public static final byte PROTO_SOCKS5 = 0x05;

    public static InetAddress readAddress(AddressType type, InputStream is) throws InvalidParameterException, IOException, IncompleteException {
        switch (type) {
            case IpV4:
                return InetAddress.getByAddress(Helper.readBytes(is, 4));
            case IpV6:
                return InetAddress.getByAddress(Helper.readBytes(is, 16));
            case Domain:
                int len = Helper.readByte(is);
                return InetAddress.getByName(new String(Helper.readBytes(is, len)));
        }

        throw new InvalidParameterException("Unknown address type");
    }


    public static int readByte(InputStream is) throws IOException, IncompleteException {
        int result = is.read();
        if (result < 0) {
            throw new IncompleteException();
        }

        return result;
    }

    public static byte[] readBytes(InputStream is, int len) throws IOException, IncompleteException {
        byte[] result = new byte[len];
        int readed = is.read(result);
        if (readed < 0) {
            throw new IncompleteException();
        }

        if (readed < result.length) {
            throw new IncompleteException();
        }

        return result; }
}
