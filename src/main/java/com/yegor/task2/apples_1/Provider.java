package com.yegor.task2.apples_1;

/**
 * This class represents a provider that delivers the apples to the warehouse.
 * Provider delivers apples at once when necessary.
 * Created by YegorKost on 08.02.2017.
 */
public class Provider {

    public static int getApplesFromProvider(int i) {
        System.out.println("Provider delivers " + i + " apples to warehouse");
        return i;
    }

}
