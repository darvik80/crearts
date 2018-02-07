package xyz.crearts.proxy.domain.socks5;

/**
 * @author ivan.kishchenko
 */
public class HandshakeResponse {
    private AuthType authType;

    public HandshakeResponse() {
        this.authType = AuthType.AUTH_USERNAME;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public byte[] getBytes() {
        return new byte[] { Helper.PROTO_SOCKS5,  this.authType.toByte() };
    }
}
