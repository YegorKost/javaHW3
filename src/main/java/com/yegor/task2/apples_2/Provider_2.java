package com.yegor.task2.apples_2;

/**
 * This class represents a provider that delivers the apples to the warehouse.
 * Provider delivers apples periodically.
 * Created by YegorKost on 11.02.2017.
 */
public class Provider_2 implements Runnable {
    private Warehouse_2 warehouse_2;
    private int velumOfDelivers;

    public Provider_2(Warehouse_2 warehouse_2, int velumOfDelivers) {
        this.warehouse_2 = warehouse_2;
        this.velumOfDelivers = velumOfDelivers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Provider delivery apples: " + velumOfDelivers);
                warehouse_2.setCapacity(velumOfDelivers);
                Warehouse_2.getOrder().countDown();
                Thread.sleep(8000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
