package com.yegor.task5.version_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents the flow of a week
 * Created by YegorKost on 10.02.2017.
 */
public class Week implements Runnable {
    private List<String> days = new ArrayList<>();

    public Week() {
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
        days.add("Monday");
    }
    @Override
    public void run() {

        while (!Thread.interrupted()) {

            String day = days.get(7 - (int)Condition.getWeek().getCount());
            Condition.getChangeDay().countDown(); // start new day
            try {
                Thread.sleep(1000);  // flowing of the day
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(day + ": " + Condition.getPlayers() + " friends want to play football");
            if (Condition.getWeek().getCount() == 1 ) { // check if the Monday
                String weather = ThreadLocalRandom.current().nextInt(2) == 1 ? "sunny" : "rainy"; // generate the weather
                if (Condition.getPlayers() == 10 && weather.equals("sunny")){
                    System.out.println("The weather is " + weather + " and " +
                            Condition.getPlayers() + " friends are coming. Friends play football! :)");
                } else {
                    System.out.println("The weather is " + weather + ". " + Condition.getPlayers() +
                            " friends have wanted to play football. Friends maybe play football next time!");
                }
                System.out.println("--------------------------------------");
                synchronized (Condition.class) {
                    Condition.setPlayers(0); // for new week resets the registered "friends"
                }
                Condition.getWeek().countDown(); // finishes a week
                Condition.setWeek(new CountDownLatch(7)); // sets a new week
            } else {
                Condition.getWeek().countDown(); // if the day isn't the Monday then finish the day and begin another one
            }
        }

    }
}
