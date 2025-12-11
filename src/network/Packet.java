package network;

/**
 * Represents a simplified network packet with source and destination addresses,
 * payload data, and a checksum for error detection.
 */
public class Packet {
    private int srcAddr;
    private int destAddr;
    private String payload;
    private int checksum;

    /**
     * Constructs a new Packet with given source, destination, and payload.
     * Automatically calculates checksum.
     */
    public Packet(int src, int dest, String data) {
        this.srcAddr = src;
        this.destAddr = dest;
        this.payload = data;
        this.checksum = calculateChecksum();
    }

    /**
     * Calculates a simple checksum based on payload characters.
     * @return checksum value (0–255)
     */
    private int calculateChecksum() {
        int sum = 0;
        for (char c : payload.toCharArray()) {
            sum += c;
        }
        return sum % 256;
    }

    /**
     * Verifies whether the stored checksum matches recalculated checksum.
     * @return true if checksum is valid, false otherwise
     */
    public boolean verifyChecksum() {
        return this.checksum == calculateChecksum();
    }

    // Getters
    public int getSrcAddr() { return srcAddr; }
    public int getDestAddr() { return destAddr; }
    public String getPayload() { return payload; }
    public int getChecksum() { return checksum; }
}