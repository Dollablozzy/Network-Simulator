package network;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FlowControlTest {

    @Test
    public void testStopAndWaitOutput() {
        FlowControl fc = new FlowControl();
        Packet p = new Packet(1, 2, "TestPayload");

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        fc.stopAndWait(p);

        // Restore System.out
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue("Output should mention sending packet", output.contains("Sending packet: TestPayload"));
        assertTrue("Output should mention waiting for ACK", output.contains("Waiting for ACK..."));
        assertTrue("Output should mention ACK received", output.contains("ACK received for packet."));
    }

    @Test
    public void testSlidingWindowOutput() {
        FlowControl fc = new FlowControl();
        Packet[] packets = {
            new Packet(1, 2, "Packet A"),
            new Packet(1, 2, "Packet B"),
            new Packet(1, 2, "Packet C")
        };

        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        fc.slidingWindow(packets, 2);

        // Restore System.out
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue("Output should mention Packet A", output.contains("Sending packet: Packet A"));
        assertTrue("Output should mention Packet B", output.contains("Sending packet: Packet B"));
        assertTrue("Output should mention Packet C", output.contains("Sending packet: Packet C"));
        assertTrue("Output should mention ACKs received", output.contains("ACKs received for window."));
    }
}