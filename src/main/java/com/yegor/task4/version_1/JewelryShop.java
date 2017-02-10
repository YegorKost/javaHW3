package com.yegor.task4.version_1;

import java.util.concurrent.CountDownLatch;

/**
 * This class represents the work of the jewelry shop
 * Created by YegorKost on 09.02.2017.
 */
public class JewelryShop implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                if (Conditions.getNearTheShop().getCount() > 0) {
                    System.out.println("The shop is closed!");
                    waitCustomersOndOpenTheDoor(); // check the number of customers and await
                } else {
                    if (Conditions.getAtTheShop().availablePermits() > 1) { // if the customers at the shop less then 4
                        Conditions.setDoor(new CountDownLatch(1)); // closes the door
                        Conditions.setNearTheShop(new CountDownLatch(4)); // awaits 4 customers near the shop
                        System.out.println("The shop is closed!");
                        Thread.sleep(10000);
                        waitCustomersOndOpenTheDoor(); // after the break check the number of customers and await
                    } else  {
                        Thread.sleep(500);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitCustomersOndOpenTheDoor() throws InterruptedException {
        Conditions.getNearTheShop().await(); // awaits 4 customers
        Conditions.getDoor().countDown(); // opens the door
        System.out.println("The shop is opened! (Customers: " +
                (5 - Conditions.getAtTheShop().availablePermits()) + ")");
    }
}
