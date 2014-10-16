package com.testproject.models;

import java.util.Random;

/**
 * Created by Mark on 16/10/2014.
 */
public class GenerateData {

    public int generateNumber() {
        Random rnd = new Random();
        int randomNum = rnd.nextInt(900) + 100;
        System.out.println("The random integer is: "+ randomNum);
        return randomNum;
    }
}
