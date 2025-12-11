package network;

/**
 * Implements basic flow control mechanisms:
 * - Stop-and-Wait
 * - Sliding Window
 */
public class FlowControl {

    /**
     * Demonstrates Stop-and-Wait flow control.
     * Sends one packet and waits for acknowledgment.
     */
    public void stopAndWait(Packet packet) {
        System.out.println("Sending packet: " + packet.getPayload());
        System.out.println("Waiting for ACK...");
        System.out.println("ACK received for packet.");
    }

    /**
     * Demonstrates Sliding Window flow control.
     * Sends packets in batches defined by window size.
     */
    public void slidingWindow(Packet[] packets, int windowSize) {
        int start = 0;
        while (start < packets.length) {
            int end = Math.min(start + windowSize, packets.length);
            for (int i = start; i < end; i++) {
                System.out.println("Sending packet: " + packets[i].getPayload());
            }
            System.out.println("ACKs received for window.");
            start = end;
        }
    }
}