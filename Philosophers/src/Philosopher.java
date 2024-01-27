import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Philosopher implements Runnable {
    private final int id;
    private final Lock[] forks; // Массив вилок
    private int eatCount;

    private static final int MAX_PHILOSOPHERS_EATING = 4;
    private static final Lock eatingLimitLock = new ReentrantLock();
    private static int philosophersEatingCount = 0;

    public Philosopher(int id, Lock[] forks) {
        this.id = id;
        this.forks = forks;
        this.eatCount = 0;
    }

    private void tryPickUpForks() throws InterruptedException {
        eatingLimitLock.lock();
        try {
            // Берем вилку с меньшим номером сначала, а затем вилку с большим номером
            int firstFork = id % forks.length;
            int secondFork = (id + 1) % forks.length;

            forks[firstFork].lock();
            forks[secondFork].lock();
            philosophersEatingCount++;
            System.out.println("Философ " + id + " взял вилки " + (firstFork + 1) + " и " + (secondFork + 1));
        } finally {
            eatingLimitLock.unlock();
        }
    }


    private void finishEating() {
        int firstFork = id % forks.length;
        int secondFork = (id + 1) % forks.length;

        try {
            forks[firstFork].unlock();
            forks[secondFork].unlock();
        } catch (IllegalMonitorStateException e) {
            // Обработка исключения IllegalMonitorStateException
            System.err.println("Философ " + id + " столкнулся с проблемой при освобождении вилок.");
            e.printStackTrace();
        }

        eatingLimitLock.lock();
        try {
            philosophersEatingCount--;
            System.out.println("Философ " + id + " положил вилки " + (firstFork + 1) + " и " + (secondFork + 1));
        } finally {
            eatingLimitLock.unlock();
        }
    }


    private void eat() {
        System.out.println("Философ " + id + " начал есть");
        eatCount++;
    }

    private void think() {
        System.out.println("Философ " + id + " размышляет");
    }

    @Override
    public void run() {
        try {
            while (eatCount < 3) {
                think();
                Thread.sleep(500); // Имитация времени размышлений

                tryPickUpForks();
                eat();
                finishEating();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
