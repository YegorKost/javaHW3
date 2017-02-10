package com.yegor.task1;

import java.util.Random;

/**
 * This class represents the work of threads
 * Created by YegorKost on 08.02.2017.
 */
class SomeThread extends Thread {
    private Random random = new Random();

    public SomeThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread: " + Thread.currentThread().getName() + " is started");
            Thread.sleep(random.nextInt(5000 - random.nextInt(5000 - 1000)));
            System.out.println("Thread: " + Thread.currentThread().getName() + " is finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TestOrdering {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new SomeThread("Thread-1");
        Thread thread2 = new SomeThread("Thread-2");
        Thread thread3 = new SomeThread("Thread-3");
        Thread thread4 = new SomeThread("Thread-4");
        thread1.start();
        thread1.join();
        thread2.start();
        thread3.start();
        thread2.join();
        thread3.join();
        thread4.start();
    }
}
