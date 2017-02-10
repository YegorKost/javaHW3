package com.yegor.task4.version_1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * This class represents conditions for the work of the shop
 * Created by YegorKost on 09.02.2017.
 */
public class Conditions {
    private static Semaphore atTheShop;
    private static CountDownLatch door;
    private static CountDownLatch nearTheShop;

    public static Semaphore getAtTheShop() {
        return atTheShop;
    }

    public static void setAtTheShop(Semaphore atTheShop) {
        Conditions.atTheShop = atTheShop;
    }

    public static CountDownLatch getDoor() {
        return door;
    }

    public static void setDoor(CountDownLatch door) {
        Conditions.door = door;
    }

    public static CountDownLatch getNearTheShop() {
        return nearTheShop;
    }

    public static void setNearTheShop(CountDownLatch nearTheShop) {
        Conditions.nearTheShop = nearTheShop;
    }
}
