package com.yegor.task2.apples_1;

/**
 * This class represents a warehouse that delivers the apples to the stores
 * Created by YegorKost on 08.02.2017.
 */
public class Warehouse {

    private int capacity;
    private int apples;

    public Warehouse(int capacity) {
        this.capacity = capacity;
    }

    public synchronized int getApplesFromWarehouse(int i) {
        if ((apples - i) < 0) {
            System.out.println("Warehouse doesn't have enough apples (" + apples + ")\n" +
                    "Warehouse requests " + capacity + " apples from provider");
            int applesFromProvider = Provider.getApplesFromProvider(capacity);
            System.out.println("Warehouse takes " + applesFromProvider + " apples from provider");
            apples += applesFromProvider;
            System.out.println("Warehouse has " + apples + " apples");
        }
        apples -= i;
        return i;
    }
}
