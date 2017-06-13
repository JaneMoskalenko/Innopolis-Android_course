package com.Innopolis;

import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        int maxSize=10;
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(maxSize);
        CubbyHole c = new CubbyHole(hmap);
        Producer p1 = new Producer(c, hmap, maxSize);
        Consumer c1 = new Consumer(c);
        p1.start();
        c1.start();

        try {
            p1.join();
            c1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
