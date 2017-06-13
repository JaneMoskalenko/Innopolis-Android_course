package com.Innopolis;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by admin on 13.06.2017.
 */
class Producer extends Thread {
    private CubbyHole cubbyhole;
    HashMap<Integer, Integer> hmap;
    private int number;
    private int maxSize;
    Random random = new Random();

    public Producer(CubbyHole c, HashMap<Integer, Integer> hmap, int maxSize) {
        this.cubbyhole = c;
        this.hmap=hmap;
        this.maxSize=maxSize;
    }
    public synchronized boolean getSize(HashMap<Integer, Integer> hmap){
        return hmap.size()<maxSize;
    }

    public void run() {
        while (getSize(hmap)){
            number = (int) (random.nextInt(20));
            System.out.println("Generated : "+ number);
            try {
                sleep(1000);
            } catch (InterruptedException e) { }
            cubbyhole.put(number);

        }
    }
}
