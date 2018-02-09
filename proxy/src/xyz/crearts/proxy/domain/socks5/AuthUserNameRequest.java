package xyz.crearts.proxy.domain.socks5;

import lombok.Data;
import xyz.crearts.proxy.exception.IncompleteException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;

/**
 * @author ivan.kishchenko
 */
/*
+----+------+----------+------+----------+
|VER | ULEN |  UNAME   | PLEN |  PASSWD  |
+----+------+----------+------+----------+
| 1  |  1   | 1 to 255 |  1   | 1 to 255 |
+----+------+----------+------+----------+
 */
@Data
public class AuthUserNameRequest {
    private String username;
    private String password;

    public AuthUserNameRequest read(InputStream is) throws InvalidParameterException, IOException, IncompleteException {
        if (Helper.readByte(is) != 0x01) {
            throw new InvalidParameterException("Unsupported protocol");
        }

        this.username = new String(Helper.readBytes(is, Helper.readByte(is)));
        this.password = new String(Helper.readBytes(is, Helper.readByte(is)));

        return this;
    }

}
