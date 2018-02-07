package xyz.crearts.proxy.domain.socks5;

public enum OperationType {
    Unknown,
    Connection,
    Bind,
    UDPAssociate;

    public static OperationType fromByte(int type) {
        switch (type) {
            case 0x01:
                return Connection;
            case 0x02:
                return Bind;
            case 0x03:
                return UDPAssociate;
            default:
                return Unknown;
        }
    }

    public byte toByte() {
        switch (this) {
            case Connection:
                return 0x01;
            case Bind:
                return 0x02;
            case UDPAssociate:
                return 0x02;
            default:
                return 0x00;
        }
    }
}
