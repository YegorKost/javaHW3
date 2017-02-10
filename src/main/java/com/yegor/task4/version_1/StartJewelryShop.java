package com.yegor.task4.version_1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * This class starts a jewelry shop and customers
 * Created by YegorKost on 09.02.2017.
 */
public class StartJewelryShop {
    public static void main(String[] args) {
        Conditions.setNearTheShop(new CountDownLatch(4)); // initializes condition for opening the shop (4 customers)
        Conditions.setDoor(new CountDownLatch(1)); // for opening and closing the door
        Conditions.setAtTheShop(new Semaphore(5)); // restricts the number of customers in the shop

        Thread shop = new Thread(new JewelryShop());
        shop.start();

        try {
            for (int i = 1; i <= 20; i++) {
                new Thread(new Customer(i)).start(); // 20 customers want to go to the shop
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
