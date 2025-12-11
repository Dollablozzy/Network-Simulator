package network;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoutingTableTest {

    @Test
    public void testAddAndLookupRoute() {
        RoutingTable rt = new RoutingTable();
        rt.addRoute(2, 99);

        int nextHop = rt.lookup(2);
        assertEquals("Next hop for destination 2 should be 99", 99, nextHop);
    }

    @Test
    public void testLookupNonexistentRoute() {
        RoutingTable rt = new RoutingTable();

        int nextHop = rt.lookup(5);
        assertEquals("Lookup for nonexistent destination should return -1", -1, nextHop);
    }

    @Test
    public void testOverwriteRoute() {
        RoutingTable rt = new RoutingTable();
        rt.addRoute(2, 99);
        rt.addRoute(2, 100); // overwrite existing route

        int nextHop = rt.lookup(2);
        assertEquals("Next hop should be updated to 100", 100, nextHop);
    }
}