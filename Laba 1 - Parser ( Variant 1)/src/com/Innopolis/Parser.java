package com.Innopolis;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jane Moskalenko on 11.06.2017.
 */
public class Parser {
    private  HashMap<String, Integer> hmap;
    private volatile Boolean mException=false;

    public Parser( HashMap<String, Integer> hmap) {
        this.hmap = hmap;
        mException=false;
    }

    public boolean myException(){
        return mException;
    }
    public void read(String str) {
        Pattern patternAllowed = Pattern.compile(
                "[" +                   //начало списка допустимых символов
                        "а-яА-ЯёЁ." +    //буквы русского алфавита
                        "\\d" +         //цифры
                        "\\x20" +         //пробел
                        "\\p{Punct}" +  //знаки пунктуации
                        "]" +                   //конец списка допустимых символов
                        "*");                   //допускается наличие указанных символов в любом количестве
        Pattern patternBlocked = Pattern.compile("[a-zA-Z]");
        Matcher matcherAllowed = patternAllowed.matcher(String.valueOf(str));
        // проверка на допустимые символы
        if (matcherAllowed.matches()) {
            synchronized (hmap) {
                // поиск слова в хешмапе
                    if (!hmap.containsKey(str)) {
                        //добавление нового слова
                        hmap.put(str, 1);
                    } else {
                        // обновление значения в хешмапе
                        hmap.put(str, hmap.get(str) + 1);
                    }
                    System.out.println(str + " - " + hmap.get(str));
                    mException = false;
            }
        }
         else {
            Matcher matcherBlocked = patternBlocked.matcher(String.valueOf(str));
            // проверка на латинские символы
            if (matcherBlocked.matches()) {
                mException = true;
                System.out.println("LATIN SYMBOL!");
                return;
            }
        }
    }


}
