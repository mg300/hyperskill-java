package org.example;

import org.example.bot.AiBot;
import org.example.bot.Bot;

import java.util.Scanner;
import java.util.function.Consumer;

public class ChatBot {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Consumer<String> output = System.out::println;
        Bot bot = new AiBot(scanner,output);
        bot.run();
    }
}