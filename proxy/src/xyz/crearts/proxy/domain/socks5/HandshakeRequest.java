package xyz.crearts.proxy.domain.socks5;

import lombok.Data;
import xyz.crearts.proxy.exception.IncompleteException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ivan.kishchenko
 */
@Data
public class HandshakeRequest {
    private Set<AuthType> authList = new HashSet<>();

    public HandshakeRequest() {

    }

    public HandshakeRequest read(InputStream is) throws InvalidParameterException, IOException, IncompleteException {
        if (Helper.readByte(is) != Helper.PROTO_SOCKS5) {
            throw new InvalidParameterException("Message data is too short");
        }

        int count = Helper.readByte(is);

        while (count-- > 0) {
            AuthType type = AuthType.fromByte(Helper.readByte(is));
            if (type != AuthType.AUTH_UNSUPPORT) {
                authList.add(type);
            }
        }

        return this;
    }


}
