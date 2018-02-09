package xyz.crearts.proxy.domain.socks5;

import lombok.Data;
import xyz.crearts.proxy.exception.IncompleteException;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.security.InvalidParameterException;

/**
 * @author ivan.kishchenko
 */
@Data
public class CommandRequest {
    private OperationType operationType;
    private AddressType addressType;
    private InetAddress address;
    private int port;

    public CommandRequest read(InputStream is) throws InvalidParameterException, IOException, IncompleteException {
        if (Helper.readByte(is) != Helper.PROTO_SOCKS5) {
            throw new InvalidParameterException("Message data is too short");
        }

        this.operationType = OperationType.fromByte(Helper.readByte(is));
        if (this.operationType == OperationType.Unknown) {
            throw new InvalidParameterException("Unknown connection operation type");
        }

        // ignore byte
        Helper.readByte(is);

        this.addressType = AddressType.fromByte(Helper.readByte(is));
        this.address = Helper.readAddress(this.addressType, is);

        int high = Helper.readByte(is);
        int low = Helper.readByte(is);

        this.port =(high << 8) + low;

        return this;
    }
}
