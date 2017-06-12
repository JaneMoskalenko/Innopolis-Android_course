package com.Innopolis;

import com.Innopolis.Models.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;

public class Main {


    public static void main(String[] args) throws Exception {
        Student s = new Student("Connor", "John", "Reese", new Date(), 0l);

        System.out.println( s.getClass().getName());

        StringBuffer str = new StringBuffer();
       // for (Field f : s.getClass().getDeclaredFields()) {

        System.out.println(s.getClass().getDeclaredFields());
        for (Field f : s.getClass().getDeclaredFields()) {
            System.out.println("1 " + f.getName() + " " + f.getType().toString());
        }



            for (Method m : s.getClass().getDeclaredMethods()) {
                System.out.println(m.getName() + " "
                        + m.getReturnType().toString() + " " +
                        m.getParameterTypes().length);
            }

           /* for (Annotation a : s.getClass().getAnnotations()) {
                System.out.println(a.annotationType().toString() + " " +
                        a.toString());
            } */

        Field firstName = s.getClass().getDeclaredField("contacts");
        firstName.setAccessible(true);
        System.out.println(firstName.get(s));

       /* Field studId=s.getClass().getDeclaredField("id");
        studId.setAccessible(true);
      //  studId.setLong(studId, studId.getModifiers() & ~Modifier.FINAL);
        studId.set(null,1l);
        System.out.println(studId.get(s));*/

    }
}
