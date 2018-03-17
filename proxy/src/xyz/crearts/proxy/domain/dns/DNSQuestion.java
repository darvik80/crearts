package xyz.crearts.proxy.domain.dns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DNSQuestion {
    private String name;
    private int qtype;
    private int qclass;
}
