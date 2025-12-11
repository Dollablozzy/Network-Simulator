## Overview

This application simulates basic network operations including:
- **Packet Creation**: Constructs network packets with source/destination addresses, payload data, and checksum error detection
- **Routing**: Implements a routing table for packet forwarding decisions
- **Network Simulation**: Simulates packet transmission through a network
- **Flow Control**: Demonstrates two flow control algorithms:
  - Stop-and-Wait Protocol
  - Sliding Window Protocol
**JUnit 4 Tests** for all major modules

## Project Structure

```
Networking-Stack-Simulation-main/
├── bin/                        # Compiled .class files (output directory)
│   └── network/
│       ├── FlowControl.class
│       ├── FlowControlTest.class
│       ├── Main.class
│       ├── MainTest.class
│       ├── NetworkSimulator.class
│       ├── NetworkSimulatorTest.class
│       ├── Packet.class
│       ├── PacketTest.class
│       ├── RoutingTable.class
│       └── RoutingTableTest.class
├── libs/                       # External libraries for testing
│   ├── hamcrest-core-1.3.jar
│   └── junit-4.13.2.jar
├── src/
│   └── network/
│       ├── FlowControl.java        # Flow control algorithms (Stop-and-Wait, Sliding Window)
│       ├── Main.java              # Entry point and demonstration
│       ├── NetworkSimulator.java  # Core network simulation (sends/routs packets)
│       ├── Packet.java            # Packet representation with checksum
│       └── RoutingTable.java      # Simple routing table abstraction
└── test/
    └── network/
        ├── FlowControlTest.java   # JUnit tests for flow control
        ├── MainTest.java          # JUnit tests for demo behavior
        ├── NetworkSimulatorTest.java
        ├── PacketTest.java
        └── RoutingTableTest.java
```

## Components

### Packet.java
Represents a network packet with:
- Source address
- Destination address
- Payload data
- Checksum for error detection (calculated automatically)

**Key Methods:**
- `Packet(int src, int dest, String data)` - Creates a new packet
- `verifyChecksum()` - Validates packet integrity
- Getters for all packet fields

### RoutingTable.java
Implements a simple routing table using a HashMap to map destination addresses to next-hop addresses.

**Key Methods:**
- `addRoute(int dest, int nextHop)` - Adds a routing entry
- `lookup(int dest)` - Returns next-hop address or -1 if no route exists

### NetworkSimulator.java
Simulates a network environment that routes packets based on the routing table.

**Key Methods:**
- `addRoute(int dest, int nextHop)` - Adds a route to the routing table
- `sendPacket(Packet packet)` - Sends a packet and performs routing lookup

### FlowControl.java
Demonstrates two flow control algorithms:

1. **Stop-and-Wait Protocol**: Sends one packet at a time, waiting for acknowledgment before sending the next
   - `stopAndWait(Packet packet)` - Demonstrates single-packet transmission

2. **Sliding Window Protocol**: Sends multiple packets in a window, advancing as acknowledgments are received
   - `slidingWindow(Packet[] packets, int windowSize)` - Sends packets in batches

### Main.java
Entry point that demonstrates the application's functionality by:
1. Creating a network simulator and adding routes
2. Creating packets
3. Verifying packet checksums
4. Sending packets through the network
5. Demonstrating flow control mechanisms

## Test File Overview

Each module has a dedicated unit test for verification.

### PacketTest.java

-   Validates checksum correctness\
-   Ensures proper field assignments\
-   Detects checksum mismatch when payload changes

### RoutingTableTest.java

-   Verifies route addition and lookup\
-   Handles unknown destinations correctly

### NetworkSimulatorTest.java

-   Ensures routing logic is applied correctly\
-   Confirms simulator behavior when no route exists

### FlowControlTest.java

-   Tests both Stop‑and‑Wait and Sliding Window mechanisms

### MainTest.java

-   Ensures the demo executes without errors

## Prerequisites

- **Java Development Kit (JDK)** version 8 or higher
- A Java compiler and runtime environment

## Compilation

Navigate to the `project-root` directory and compile the source files:

```bash
javac -d bin src/network/*.java
```

This will compile all Java source files in the `src/network/` directory and place the compiled `.class` files in the `bin/network/` directory.

## Running the Application

After compilation, run the application from the `project-root` directory:

```bash
java -cp bin network.Main
```

Or on Windows:
```bash
java -cp bin network.Main
```

## Example Output

 you should see output similar to:

```
Checksum valid? true
Packet from 1 to 2 routed via 99
Sending packet: Hello, Node 2!
Waiting for ACK...
ACK received for packet.
Sending packet: Packet A
Sending packet: Packet B
ACKs received for window.
Sending packet: Packet C
ACKs received for window.
```
## Commands for testing functionality of each Test.java file (Run these commands in project root)
java -cp "bin;libs/junit-4.13.2.jar;libs/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore network.MainTest
java -cp "bin;libs/junit-4.13.2.jar;libs/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore network.PacketTest
java -cp "bin;libs/junit-4.13.2.jar;libs/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore network.RoutingTableTest
java -cp "bin;libs/junit-4.13.2.jar;libs/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore network.NetworkSimulatorTest
java -cp "bin;libs/junit-4.13.2.jar;libs/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore network.FlowControlTest

## Command to run every Test.java file at once 
java -cp "bin;libs/junit-4.13.2.jar;libs/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore network.MainTest network.PacketTest network.RoutingTableTest network.NetworkSimulatorTest network.FlowControlTest


## Usage Example

The main method demonstrates a typical usage:

```java
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
```