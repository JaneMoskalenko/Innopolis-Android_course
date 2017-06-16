package com.Innopolis;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Jane Moskalenko on 11.06.2017.
 */
public class Parser {
    private CopyOnWriteArrayList<Integer> collection;
    private volatile Boolean mException=false;
    private Stream<Integer> streamFromCollection;
    ReentrantLock locker = new ReentrantLock();

    public Parser( CopyOnWriteArrayList<Integer> collection) {
        this.collection = collection;
        mException=false;
        streamFromCollection = collection.stream();
    }

    public boolean myException(){
        return mException;
    }

    public boolean isAllowed(String str){
        Pattern patternAllowed = Pattern.compile(
                        "[" +                   //начало списка допустимых символов
                        "-" +    //буквы русского алфавита
                        "\\d" +         //цифры
                        "\\x20" +         //пробел
                        "]" +                   //конец списка допустимых символов
                        "*");                   //допускается наличие указанных символов в любом количестве
        Matcher matcherAllowed = patternAllowed.matcher(String.valueOf(str));
        return matcherAllowed.matches();
    }

    public boolean isBlocked(String str) {
        Pattern patternBlocked = Pattern.compile("[a-zA-Z]");
        Matcher matcherBlocked = patternBlocked.matcher(String.valueOf(str));
        return matcherBlocked.matches();

    }

    public boolean isGreaterZeroAndEven(Integer x) {
        return ((x>0)&&(x%2==0));
    }
    public Integer getSumStream(){
        return collection.stream().filter((s)->s>0&&s%2==0).reduce((s1, s2) -> s1 + s2).orElse(0);
    }


    public void read(String str) {
        // проверка на допустимые символы
        if (isAllowed(str)) {
            int x = Integer.parseInt(str);
            locker.lock();
            try {
                collection.add(x);
                System.out.println(Thread.currentThread().getName() + " added " + x);
                System.out.println(getSumStream());
            } finally {
                locker.unlock();
            }
            mException = false;
        }
         else {
            // проверка на латинские символы
            if (isBlocked(str)) {
                mException = true;
                System.out.println("LATIN SYMBOL!");
            }
        }
    }


}
