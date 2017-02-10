package com.yegor.task5.version_1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a "friend" who decides whether go to football
 * on Monday or not.
 * Created by YegorKost on 10.02.2017.
 */
public class Friend implements Runnable {
    private boolean goToFootball = false;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (!goToFootball) {
                    Condition.getChangeDay().await(); // await the beginning of a new day of a week
                    int r = ThreadLocalRandom.current().nextInt(20);
                    if ( r < 9) {  // "friend" decides whether go to football on Monday
                        goToFootball = true; // "friend" has decided to go to football on Monday
                        synchronized (Condition.class) {
                            Condition.incrementPlayers(); // "friend" has registered
                        }
                    }
                } else {
                    Condition.getWeek().await(); // "friend" has registered and awaits the Monday
                    goToFootball = false; // after the game reset the flag and "friend" may register to new game
                }
                if (Condition.getChangeDay().getCount() == 0){     // the first "friend" who doesn't registered
                    Condition.setChangeDay(new CountDownLatch(1)); // and sets new latch for new day
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
