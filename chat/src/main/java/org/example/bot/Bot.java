package org.example.bot;

public interface Bot {
    void greetings();
    void guessAge();
    void count();
    void quiz();
    void bye();
    default void run(){
        greetings();
        guessAge();
        count();
        quiz();
        bye();
    }
}
