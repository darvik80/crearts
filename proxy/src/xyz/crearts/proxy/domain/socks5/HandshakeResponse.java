package xyz.crearts.proxy.domain.socks5;

import lombok.Data;

/**
 * @author ivan.kishchenko
 */
@Data
public class HandshakeResponse {
    private AuthType authType;

    public HandshakeResponse() {
        this.authType = AuthType.AUTH_USERNAME;
    }

    public byte[] getBytes() {
        return new byte[] { Helper.PROTO_SOCKS5,  this.authType.toByte() };
    }
}
