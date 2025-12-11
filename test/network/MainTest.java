package network;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void testMainRunsWithoutErrors() {
        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method
        Main.main(new String[]{});

        // Restore System.out
        System.setOut(System.out);

        // Check that output contains expected phrases
        String output = outContent.toString();
        assertTrue("Output should mention checksum validation", output.contains("Checksum valid?"));
        assertTrue("Output should mention routing", output.contains("routed"));
        assertTrue("Output should mention Stop-and-Wait", output.contains("ACK received"));
        assertTrue("Output should mention Sliding Window", output.contains("Packet A"));
    }
}