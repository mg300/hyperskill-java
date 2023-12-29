package quiz;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Quiz {
    private final String question;
    private final List<String> answers;
    private final int correctAnswerIndex;

    public Quiz(String question, List<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }
    public void ask(Consumer<String> output, Scanner scanner){
        output.accept(question);
        int index = 1;
        for (String answer : answers){
            output.accept(index+". "+answer);
            index++;
        }
        while((scanner.nextInt()-1) != correctAnswerIndex) {
            output.accept("Please, try again.");
        }


    }

}
