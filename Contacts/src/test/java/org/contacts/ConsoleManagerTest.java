package org.contacts;

import org.contacts.IO.ConsoleManager;
import org.contacts.IO.IOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleManagerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    void printText() {
        String outputText = "abcds";
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Consumer<String> output = System.out::println;
        IOManager ioManager = new ConsoleManager(scanner, output);
        ioManager.printText(outputText);
        assertEquals(outputText, outContent.toString().trim());
    }

    @Test
    void readLine() {
        String input = "abcds";
        provideInput(input);
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Consumer<String> output = System.out::println;
        IOManager io = new ConsoleManager(scanner, output);
        String readline = io.readLine();
        assertEquals(input, readline);
    }

    @Test
    void readIntInput() {
        String input = "abcds\nwitam\n-1\n!1\n01\n2s0\n4f\n1s\ns1\n1\n";
        provideInput(input);
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Consumer<String> output = System.out::println;
        IOManager io = new ConsoleManager(scanner, output);
        int readline = io.readIntInput();
        assertEquals(1, readline);
    }


    void provideInput(String text) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(text.getBytes());
        System.setIn(testIn);
    }
}