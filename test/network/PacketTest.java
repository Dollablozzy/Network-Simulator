package network;

import org.junit.Test;
import static org.junit.Assert.*;

public class PacketTest {

    @Test
    public void testChecksumIsCalculatedCorrectly() {
        Packet p = new Packet(1, 2, "ABC");
        int expectedChecksum = ('A' + 'B' + 'C') % 256;
        assertEquals("Checksum should match calculated value", expectedChecksum, p.getChecksum());
    }

    @Test
    public void testVerifyChecksumValid() {
        Packet p = new Packet(1, 2, "Hello");
        assertTrue("Checksum should be valid for original payload", p.verifyChecksum());
    }

    @Test
    public void testVerifyChecksumInvalidAfterTampering() {
        Packet p1 = new Packet(1, 2, "Hello");
        Packet p2 = new Packet(1, 2, "Hell0"); // slightly different payload
        assertNotEquals("Checksums should differ for different payloads", p1.getChecksum(), p2.getChecksum());
    }

    @Test
    public void testGettersReturnCorrectValues() {
        Packet p = new Packet(10, 20, "Data");
        assertEquals("Source address should match", 10, p.getSrcAddr());
        assertEquals("Destination address should match", 20, p.getDestAddr());
        assertEquals("Payload should match", "Data", p.getPayload());
    }
}