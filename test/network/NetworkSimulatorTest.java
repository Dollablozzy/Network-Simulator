package network;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NetworkSimulatorTest {

    @Test
    public void testSendPacketWithValidRoute() {
        NetworkSimulator simulator = new NetworkSimulator();
        simulator.addRoute(2, 99);
        Packet p = new Packet(1, 2, "Hello");

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        simulator.sendPacket(p);

        // Restore System.out
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue("Output should mention routing via 99",
                   output.contains("Packet from 1 to 2 routed via 99"));
    }

    @Test
    public void testSendPacketWithNoRoute() {
        NetworkSimulator simulator = new NetworkSimulator();
        Packet p = new Packet(1, 5, "NoRouteTest");

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        simulator.sendPacket(p);

        // Restore System.out
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue("Output should mention no route",
                   output.contains("No route to destination 5"));
    }
}