package com.yegor.task4.version_1;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents customers of the jewelry shop
 * Created by YegorKost on 09.02.2017.
 */
public class Customer implements Runnable {
    private int i;

    public Customer(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("Customer " + i + " is awaiting near the shop");
            Conditions.getNearTheShop().countDown(); // customer near the shop
            Conditions.getAtTheShop().acquire(); // a customer sets into the order
            Conditions.getDoor().await(); // customer awaits when the door will be opened
            Thread.sleep(100); // enters to the shop
            System.out.println("Customer " + i + " at the shop (Customers: " + (5 - Conditions.getAtTheShop().availablePermits()) + ")");
            Thread.sleep(8000 - ThreadLocalRandom.current().nextInt(8000 - 1000)); // at the shop
            Conditions.getAtTheShop().release(); // customer go out
            System.out.println("Customer " + i + " go out!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
