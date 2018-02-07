package xyz.crearts.proxy.domain.socks5;

/*
    0x00 = запрос предоставлен
    0x01 = ошибка SOCKS-сервера
    0x02 = соединение запрещено набором правил
    0x03 = сеть недоступна
    0x04 = хост недоступен
    0x05 = отказ в соединении
    0x06 = истечение TTL
    0x07 = команда не поддерживается / ошибка протокола
    0x08 = тип адреса не поддерживается
*/

public enum ResponseType {
    Ok,
    ServerError,
    BlockByRule,
    NetworkUnavailable,
    HostUnavailable,
    ConnectionRejected,
    TTLTimeout,
    ProtocolError,
    AddressTypeNotSupport;

    public static ResponseType fromByte(byte response) {
        switch (response) {
            case 0x00:
                return Ok;
            case 0x01:
                return ServerError;
            case 0x02:
                return BlockByRule;
            case 0x03:
                return NetworkUnavailable;
            case 0x04:
                return HostUnavailable;
            case 0x05:
                return ConnectionRejected;
            case 0x06:
                return TTLTimeout;
            case 0x07:
                return ProtocolError;
            case 0x08:
                return AddressTypeNotSupport;
            default:
                return ProtocolError;
        }
    }

    public byte toByte() {
        return (byte)this.ordinal();
    }
}
