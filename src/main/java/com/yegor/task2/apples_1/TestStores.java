package com.yegor.task2.apples_1;

/**
 * This class represents the work of tow stores, warehouse and provider.
 * Provider delivers apples at once when necessary.
 * Created by YegorKost on 08.02.2017.
 */
public class TestStores {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(24);
        Thread store1 = new Thread(new Store(6, 2, warehouse), "CRAZY APPLE");
        Thread store2 = new Thread(new Store(9, 1, warehouse), "WILD APPLE");
        store1.start();
        store2.start();
        try {
            Thread.sleep(5000);
            store1.interrupt();
            store2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
