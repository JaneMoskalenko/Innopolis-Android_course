package com.Innopolis;

/**
 * Created by admin on 13.06.2017.
 */
class Consumer extends Thread {
    private CubbyHole cubbyhole;

    public Consumer(CubbyHole c) {
        this.cubbyhole = c;
    }
    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = cubbyhole.get();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer - " + " unique: " + value + " " + System.currentTimeMillis()/ 1000l);
        }
    }
}