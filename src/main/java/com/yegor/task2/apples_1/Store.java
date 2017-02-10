package com.yegor.task2.apples_1;

/**
 * This class represents a store
 * Created by YegorKost on 08.02.2017.
 */
public class Store implements Runnable{

    private int capacity;
    private int apples;
    private int volumeOfSales;
    private Warehouse warehouse;

    public Store(int capacity, int volumeOfSales, Warehouse warehouse) {
        this.warehouse = warehouse;
        if (volumeOfSales < capacity){
            this.capacity = capacity;
            this.volumeOfSales = volumeOfSales;
        } else {
            this.capacity = capacity;
            this.volumeOfSales = capacity;
        }
    }

    private void sellApple() {

        String store = Thread.currentThread().getName();
        if ((apples - volumeOfSales) < 0){
            System.out.println("Store " + store +
                    " doesn't have enough apples for sale (" + apples + ")\n" +
                    "Store " + store + " requests " + capacity + " apples from warehouse");
            int a = takeApplesFromWarehouse();
            System.out.println("Store " + store + " takes " + a + " apples from warehouse");
            apples += a;
            System.out.println("Store " + store + " has " + apples + " apples");
        }
        apples -= volumeOfSales;
        System.out.println("Store " + store + " sells " + volumeOfSales + " apples. Remain apples: " + apples);
    }

    private int takeApplesFromWarehouse() {
        return warehouse.getApplesFromWarehouse(capacity);
    }

    public void run() {
        try {
            while (!Thread.interrupted()){
                sellApple();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Store " + Thread.currentThread().getName() + " finishes sales");
        }
    }
}
