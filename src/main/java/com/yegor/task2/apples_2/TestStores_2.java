package com.yegor.task2.apples_2;

/**
 * This class represents the work of tow stores, warehouse and provider.
 * Created by YegorKost on 11.02.2017.
 */
public class TestStores_2 {
    public static void main(String[] args) {
        Warehouse_2 warehouse_2 = new Warehouse_2();
        Thread provider_2 = new Thread(new Provider_2(warehouse_2, 27));
        Thread store1 = new Thread(new Store_2(6, 2, warehouse_2), "CRAZY APPLE");
        Thread store2 = new Thread(new Store_2(9, 1, warehouse_2), "WILD APPLE");
        provider_2.start();
        store1.start();
        store2.start();
    }
}
