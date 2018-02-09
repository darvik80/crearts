package xyz.crearts.proxy.domain.socks5;

import lombok.Data;

import java.net.InetAddress;
import java.nio.ByteBuffer;

/**
 * @author ivan.kishchenko
 */
@Data
public class CommandResponse {
    private ResponseType responseType;
    private AddressType addressType;
    private InetAddress address;
    private int port;

    private int length() {
        int result = 6;

        switch (this.addressType) {
            case IpV4:
                result += 4;
                break;
            case IpV6:
                result += 16;
                break;
            case Domain:
                result += 1;
                if (address.getHostName() != null) {
                    result += address.getHostName().length();
                }
                break;
        }

        return result;
    }

    public byte[] getBytes() {
        ByteBuffer result = ByteBuffer.allocate(this.length());
        result.put(Helper.PROTO_SOCKS5);
        result.put(this.responseType.toByte());
        result.put((byte)0x00);
        result.put(this.addressType.toByte());
        switch (this.addressType) {
            case IpV4:
            case IpV6:
                result.put(this.address.getAddress());
                break;
            case Domain:
                result.put(this.address.getHostName().getBytes());
                break;
        }

        result.put((byte)(this.port >> 8));
        result.put((byte)(this.port));

        return result.array();

    }
}
