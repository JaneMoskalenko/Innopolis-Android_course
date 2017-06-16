package com.Innopolis;

import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> collection = new CopyOnWriteArrayList<Integer>();
        Parser parser = new Parser(collection);

        File[] files = {new File("Text.txt"), new File("Text2.txt"), new File("Text3.txt")};
        ExecutorService executor = Executors.newFixedThreadPool(3);//creating a pool of 5 threads

        for (int i = 0; i < 3; i++) {
            try {
                Runnable worker = new FileThread(files[i], collection, parser);
                executor.submit(worker);
            }
            catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            }
        }

        while (!executor.isTerminated()){
            executor.shutdown();
        }

    }
}
