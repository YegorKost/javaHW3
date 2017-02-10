package com.yegor.task5.version_1;

/**
 * Created by YegorKost on 10.02.2017.
 */
public class StartGame_1 {
    public static void main(String[] args) {
        new Thread(new Week()).start();
        for (int i = 1; i <=10; i++) {
            new Thread(new Friend()).start();
        }
    }
}
