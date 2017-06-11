package com.Innopolis;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jane Moskalenko on 11.06.2017.
 */
public class FileThread implements Runnable {
    private File myFile;
    private HashMap<String, Integer> hmap;
    private Parser parser;


    public FileThread(File myFile, HashMap<String, Integer> hmap, Parser parser) throws FileNotFoundException, UnsupportedEncodingException {
        this.myFile = myFile;
        this.hmap = hmap;
        this.parser=parser;
    }

    @Override
    public  void run() {
        while (!Thread.interrupted()) {
            try {
                parse();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void parse() throws InterruptedException {
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(myFile), "UTF-8"))){
            String line = null;
            // чтение из файла построчно в массив
            while ((line = reader.readLine()) != null) {
                String[] wordArray = line.split("[\\p{Punct}\\s]+");
                // парсинг массива по словам
                for (String str : wordArray) {
                    if (parser.myException()) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    // поиск и добавление в хешмапу
                    parser.read(str);
                    wait(500);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}


