package com.Innopolis;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        HashMap<String,Integer> hmap = new HashMap<String,Integer>();
        Parser parser = new Parser(hmap);
        File[] files = {new File("Text.txt"), new File("Text2.txt"), new File("Text3.txt")};
        ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads
        for (int i = 0; i < 3; i++) {
            try {
                Runnable worker = new FileThread(files[i], hmap, parser);
                executor.execute(worker);
            }
            catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        }
        executor.shutdown();

    }
}
