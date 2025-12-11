package network;

/**
 * Simulates a simple network environment with routing and packet transmission.
 */
public class NetworkSimulator {
    private RoutingTable routingTable = new RoutingTable();

    /**
     * Adds a route to the simulator's routing table.
     */
    public void addRoute(int dest, int nextHop) {
        routingTable.addRoute(dest, nextHop);
    }

    /**
     * Sends a packet by looking up its destination in the routing table.
     * Prints routing information or error if no route exists.
     */
    public void sendPacket(Packet packet) {
        int nextHop = routingTable.lookup(packet.getDestAddr());
        if (nextHop == -1) {
            System.out.println("No route to destination " + packet.getDestAddr());
        } else {
            System.out.println("Packet from " + packet.getSrcAddr() +
                               " to " + packet.getDestAddr() +
                               " routed via " + nextHop);
        }
    }
}