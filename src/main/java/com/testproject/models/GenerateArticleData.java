package com.testproject.models;

import org.joda.time.DateTime;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.print.DocFlavor;
import java.util.Date;
import java.util.Random;

/**
 * Created by Mark on 30/10/14.
 */
public class GenerateArticleData {

    public String generateNumber() {
        String numArray[] = { "q", "w", "e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m","1","2","3","4","5","6","7","8","9","0"};
        String id = "";

        Random rnd = new Random();
        for(int i= 0; i < 10; i++) {
            int randomNum = rnd.nextInt(36);
            id += numArray[randomNum];
        }
        System.out.print("Rnd: "+id);
        return id;
    }

    public String generateTitle(int i) {
        String title = "Article" + String.valueOf(i);
        return title;
    }

    public void sendData() {
        try {
            Jedis jedis = new Jedis("redis-demo1.cloudapp.net");
            for(int i = 0; i < 99; i++) {
                String number = String.valueOf(new DateTime().getMillis());
                jedis.lpush("articles", number);
                jedis.hset("hashedArticles", number, generateTitle(i));
            }
            jedis.close();
        } catch(JedisConnectionException e) {
            e.printStackTrace();
        }
    }
}
