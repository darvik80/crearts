package xyz.crearts.proxy.domain.socks5;

import lombok.Data;

/**
 * @author ivan.kishchenko
 */
/*
+----+--------+
|VER | STATUS |
+----+--------+
| 1  |   1    |
+----+--------+
 */
@Data
public class AuthUserNameResponse {
    private boolean status;

    public byte[] getBytes() {
        return new byte[] { 0x01,  (byte)(status ? 0 : 1) };
    }
}
