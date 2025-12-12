# Makefile for Network Simulator project (Windows version)

# Compiler
JAVAC = javac
JAVA  = java

# Directories
SRC_DIR = src/network
TEST_DIR = test/network
BIN_DIR = bin
LIB_DIR = libs

# Libraries
JUNIT = $(LIB_DIR)/junit-4.13.2.jar
HAMCREST = $(LIB_DIR)/hamcrest-core-1.3.jar
CLASSPATH = $(BIN_DIR);$(JUNIT);$(HAMCREST)

# Source and test files
SRC_FILES = $(wildcard $(SRC_DIR)/*.java)
TEST_FILES = $(wildcard $(TEST_DIR)/*.java)

# Default target
all: compile

# Compile source and test files
compile:
	$(JAVAC) -d $(BIN_DIR) -cp $(CLASSPATH) $(SRC_FILES) $(TEST_FILES)

# Run the main program
run:
	$(JAVA) -cp $(CLASSPATH) network.Main

# Run JUnit tests
test:
	$(JAVA) -cp $(CLASSPATH) org.junit.runner.JUnitCore \
        network.PacketTest \
        network.MainTest \
        network.FlowControlTest \
        network.NetworkSimulatorTest \
        network.RoutingTableTest

# Clean compiled files (Windows)
clean:
	del /Q $(BIN_DIR)\*.class $(BIN_DIR)\network\*.class

# Compile and run in one step
allrun: compile run
