package com.Innopolis;

import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Jane Moskalenko on 11.06.2017.
 */
public class FileThread implements Runnable {
    private File myFile;
    private CopyOnWriteArrayList<Integer> collection;
    private Parser parser;


    public FileThread(File myFile, CopyOnWriteArrayList<Integer> collection, Parser parser) throws FileNotFoundException, UnsupportedEncodingException {
        this.myFile = myFile;
        this.collection = collection;
        this.parser=parser;
    }

    @Override
    public  void run() {
        while (!Thread.interrupted()) {
            try {
                parseSynchronized();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void parseSynchronized() throws InterruptedException {
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(myFile), "UTF-8"))){
            String line = null;
            // чтение из файла построчно в массив
            while ((line = reader.readLine()) != null) {
                String[] wordArray = line.split("[\\s]+");
                // парсинг массива по словам
                for (String str : wordArray) {
                    if (parser.myException()) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    // поиск и добавление в хешмапу
                    parser.read(str);
                    wait(200);
                }
            }
            System.out.println("File terminated");
            Thread.currentThread().interrupt();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}


