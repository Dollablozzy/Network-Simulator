package network;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple routing table that maps destination addresses to next-hop addresses.
 */
public class RoutingTable {
    private Map<Integer, Integer> routes = new HashMap<>();

    /**
     * Adds a route to the table.
     * @param dest destination address
     * @param nextHop next-hop address
     */
    public void addRoute(int dest, int nextHop) {
        routes.put(dest, nextHop);
    }

    /**
     * Looks up the next hop for a given destination.
     * @param dest destination address
     * @return next-hop address or -1 if no route exists
     */
    public int lookup(int dest) {
        return routes.getOrDefault(dest, -1);
    }
}