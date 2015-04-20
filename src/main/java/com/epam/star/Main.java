package com.epam.star;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;

public class Main {

    private static Random rnd = new Random();


    public static void main(String[] args) throws SQLException, ParseException {

    Thread thread = new Thread();
        thread.run();

    }

    public class MyClass implements Runnable {

        @Override
        public void run() {

        }
    }
}
