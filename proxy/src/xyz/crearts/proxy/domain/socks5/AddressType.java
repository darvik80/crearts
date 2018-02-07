package xyz.crearts.proxy.domain.socks5;

public enum AddressType {
    None,
    IpV4,
    Domain,
    IpV6;

    public static AddressType fromByte(int type) {
        switch (type) {
            case 0x01:
                return IpV4;
            case 0x03:
                return Domain;
            case 0x04:
                return IpV6;
            default:
                return None;

        }
    }

    public byte toByte() {
        switch (this) {
            case IpV4:
                return 0x01;
            case Domain:
                return 0x03;
            case IpV6:
                return 0x04;
            default:
                return -1;

        }
    }
}
