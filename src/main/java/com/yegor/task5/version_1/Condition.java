package com.yegor.task5.version_1;

import java.util.concurrent.CountDownLatch;

/**
 * This class contains conditions for changes of a week and day
 * Created by YegorKost on 10.02.2017.
 */
public class Condition {
    private static CountDownLatch week = new CountDownLatch(7);
    private static CountDownLatch changeDay = new CountDownLatch(1);
    private volatile static int players;

    public static CountDownLatch getWeek() {
        return week;
    }

    public static void setWeek(CountDownLatch week) {
        Condition.week = week;
    }

    public static CountDownLatch getChangeDay() {
        return changeDay;
    }

    public static void setChangeDay(CountDownLatch changeDay) {
        Condition.changeDay = changeDay;
    }

    public static synchronized int getPlayers() {
        return players;
    }

    public static synchronized void setPlayers(int players) {
        Condition.players = players;
    }

    public static synchronized void incrementPlayers() {
        players++;
    }
}
