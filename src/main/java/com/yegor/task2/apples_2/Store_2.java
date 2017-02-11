package com.yegor.task2.apples_2;

/**
 * This class represents a store
 * Created by YegorKost on 11.02.2017.
 */
public class Store_2 implements Runnable {
    private int capacity;
    private int apples;
    private int volumeOfSales;
    private Warehouse_2 warehouse_2;

    public Store_2(int capacity, int volumeOfSales, Warehouse_2 warehouse_2) {
        this.warehouse_2 = warehouse_2;
        if (volumeOfSales < capacity){
            this.capacity = capacity;
            this.volumeOfSales = volumeOfSales;
        } else {
            this.capacity = capacity;
            this.volumeOfSales = capacity;
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                sellApple_2();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Store " + Thread.currentThread().getName() + " finishes sales");
        }
    }

    private void sellApple_2() {
        try {
            String store = Thread.currentThread().getName();
            if ((apples - volumeOfSales) < 0) {
                System.out.println("Store " + store +
                        " doesn't have enough apples for sale (" + apples + ")\n" +
                        "Store " + store + " requests " + capacity + " apples from warehouse");
                int applesFromWarehouse;
                if ((applesFromWarehouse = getFromWarehouse()) == 0){
                    System.out.println("-----The Store " + store + " awaits apples from warehouse-----");
                    Warehouse_2.getOrder().await(); // await when provider delivers apples to the warehouse
                    applesFromWarehouse = getFromWarehouse();
                    apples += applesFromWarehouse;
                } else {
                    apples += applesFromWarehouse;
                }
                System.out.println("--Store " + store + " takes " + applesFromWarehouse + " apples from warehouse--");
                System.out.println("--Store " + store + " has " + apples + " apples--");
            } else {
                apples -= volumeOfSales;
                System.out.println("Store " + store + " sells " + volumeOfSales + " apples. Remain apples: " + apples);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getFromWarehouse() {
        return warehouse_2.getApples(capacity);
    }

}
