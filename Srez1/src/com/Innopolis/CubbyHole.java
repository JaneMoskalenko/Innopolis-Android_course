package com.Innopolis;

import java.util.HashMap;

/**
 * Created by admin on 13.06.2017.
 */
class CubbyHole {
    private int contents;
    private boolean available = false;
    HashMap<Integer, Integer> hmap;

    public CubbyHole(HashMap<Integer, Integer> hmap) {
        this.hmap = hmap;
    }

    public synchronized int get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        available = false;
        notifyAll();
        return hmap.size();
    }
    public synchronized void put(int i) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!(hmap.containsKey(i))) {
            hmap.put(i, i);
            System.out.println("Producer - " + " put: " + i);
        }
        available = true;
        notifyAll();
    }
}