import java.util.ArrayList;
import java.util.List;

public class Stopwatch {
    private long startTime;
    private long elapsedTime;
    private boolean running;

    final private List<TimeObserver> observers;
    public Stopwatch() {
        startTime = 0;
        elapsedTime = 0;
        running = false;
        observers = new ArrayList<>();

    }
    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TimeObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (TimeObserver observer : observers) {
            observer.updateSystemMenu(elapsedTime);
        }
    }
    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis() - elapsedTime;
            running = true;
            Thread thread = new Thread(() -> {
                while (running) {
                    try {
                        Thread.sleep(1000);
                        elapsedTime = System.currentTimeMillis() - startTime;
                        notifyObservers();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public void stop() {
        if (running) {
            running = false;
        }
    }
    public long getElapsedTime() {
        return elapsedTime/1000;
    }
}
