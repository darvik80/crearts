package xyz.crearts.proxy.domain.socks5;

public enum AuthType {
    AUTH_NONE,
    AUTH_GSSAPI,
    AUTH_USERNAME,
    AUTH_UNSUPPORT;

    public static AuthType fromByte(int type) {
        switch (type) {
            case 0x00:
                return AUTH_NONE;
            case 0x02:
                return AUTH_USERNAME;
            default:
                return AUTH_UNSUPPORT;

        }
    }

    public byte toByte() {
        switch (this) {
            case AUTH_NONE:
                return 0x00;
            case AUTH_USERNAME:
                return 0x02;
            default:
                return -1;

        }
    }
}
