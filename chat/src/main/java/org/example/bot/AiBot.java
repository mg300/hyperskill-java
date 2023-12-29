package org.example.bot;

import quiz.Quiz;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class AiBot implements Bot {

    private final Scanner scanner;
    private final Consumer<String> output;
    private final String name = "Aid";
    private final int birthYear = 2023;

    public AiBot(Scanner scanner, Consumer<String> output) {
        this.scanner = scanner;
        this.output = output;
    }

    public void greetings(){
        output.accept("Hello my name is "+name);
        output.accept("I was created in "+birthYear);
        output.accept("Please, remind me your name.");
        output.accept("What a great name you have, " + scanner.nextLine());
    };
    public void guessAge(){
        output.accept("Enter remainders of dividing your age by 3, 5 and 7.");
        final int age =  (scanner.nextInt() * 70 + scanner.nextInt() * 21 + scanner.nextInt() * 15) % 105;
        output.accept("Your age is "+age+"; that's a good time to start programming!");

    };
    public void count(){
        output.accept("Now I will prove to you that I can count to any number you want.");
        int num = scanner.nextInt();
        for (int i =0; i<=num;i++){
            output.accept(i+"!");
        }
    };
    public void quiz(){
        String question = "Why do we use methods?";
        List<String> answers = List.of(
                "To repeat a statement multiple times.",
                "To decompose a program into several small subroutines.",
                "To determine the execution time of a program.",
                "To interrupt the execution of a program.");
        int correctIndex = 1;
        Quiz quiz = new Quiz(question, answers, correctIndex);
        quiz.ask(output, scanner);
    };
    public void bye(){
        output.accept("Congratulations, have a nice day!");
    };
}
