package com.bysj.schedul;

import java.util.Random;

public class MyThread {

    private Double currNumber;

    public MyThread(){
        int timeInterval = 1000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    Random rand = new Random();
                    currNumber = rand.nextDouble();
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public Double getCurrNumber() {
        return currNumber;
    }

    public void setCurrNumber(Double currNumber) {
        this.currNumber = currNumber;
    }
}
