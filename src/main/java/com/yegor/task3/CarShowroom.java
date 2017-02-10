package com.yegor.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * This class represents the auction at the car showroom
 * Created by YegorKost on 08.02.2017.
 */
public class CarShowroom implements Runnable{

    private static final CountDownLatch START_OF_AUCTION = new CountDownLatch(15);

    public void run() {
        System.out.println("Car showroom and customers are awaiting for auction!!!");
        while (START_OF_AUCTION.getCount() > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Auction starts!!!");
    }

    private static class Customer implements Runnable {

        private String number;

        Customer(String number) {
            this.number = number;
        }

        public void run() {
            try {
                System.out.println("Customer " + number + " has came to the car showroom");
                START_OF_AUCTION.countDown();
                START_OF_AUCTION.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Car implements Runnable {

        private String brand;

        Car(String brand) {
            this.brand = brand;
        }

        public void run() {
            try {
                System.out.println(brand + " is delivered to car showroom!");
                START_OF_AUCTION.countDown();
                START_OF_AUCTION.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<String> brands = new ArrayList<String>();
        brands.add("BMW");
        brands.add("Audi");
        brands.add("Mercedes");
        brands.add("VW");
        brands.add("Porsche");
        (new Thread(new CarShowroom())).start();
        for (String brand : brands) {
            try {
                (new Thread(new Car(brand))).start();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= 15; i++){
            try {
                (new Thread(new Customer(Integer.toString(i)))).start();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
