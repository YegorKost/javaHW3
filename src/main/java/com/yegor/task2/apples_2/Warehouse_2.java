package com.yegor.task2.apples_2;

import java.util.concurrent.CountDownLatch;

/**
 * This class represents a warehouse that delivers the apples to the stores
 * Created by YegorKost on 11.02.2017.
 */
public class Warehouse_2 {
    private int capacity;
    private static CountDownLatch order = new CountDownLatch(1);

    public synchronized int getApples(int apples) {
        if (this.capacity - apples < 0) {
            if (order.getCount() != 1) {
                order = new CountDownLatch(1); // if it's not enough apples set latch for next store
            }
            int lastApples = capacity;
            this.capacity = 0;
            return lastApples;
        } else {
            this.capacity -= apples;
            return apples;
        }
    }

    public synchronized void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static CountDownLatch getOrder() {
        return order;
    }
}
