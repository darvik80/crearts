package xyz.crearts.proxy.domain.dns;


/*

                               1  1  1  1  1  1
         0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                                               |
       /                                               /
       /                      NAME                     /
       /                                               /
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                      TYPE                     |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                     CLASS                     |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                      TTL                      |
       |                                               |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
       |                   RDLENGTH                    |
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--|
       /                     RDATA                     /
       /                                               /
       +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+

       Rental agency ask us about renewing registraion

 */

import lombok.Data;

@Data
public class DNSResourceRecord {
    private String name;
    private DNSRecordType type;
    private short recClass;
    private int ttl;

    private int rdLength;
    private byte[] rdData;
}
