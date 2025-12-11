package network;

/**
 * Entry point for the networking stack simulation.
 * Demonstrates packet creation, routing, and flow control.
 */
public class Main {
    public static void main(String[] args) {
        // Create simulator and add route
        NetworkSimulator simulator = new NetworkSimulator();
        simulator.addRoute(2, 99);

        // Create packet
        Packet p1 = new Packet(1, 2, "Hello, Node 2!");

        // Verify checksum
        System.out.println("Checksum valid? " + p1.verifyChecksum());

        // Send packet
        simulator.sendPacket(p1);

        // Flow control demo
        FlowControl fc = new FlowControl();
        fc.stopAndWait(p1);

        Packet[] packets = {
            new Packet(1, 2, "Packet A"),
            new Packet(1, 2, "Packet B"),
            new Packet(1, 2, "Packet C")
        };
        fc.slidingWindow(packets, 2);
    }
}