package com.testproject.models;

import org.springframework.util.ResourceUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.io.*;
import java.util.Random;

/**
 * Created by Mark on 16/10/2014.
 */
public class GenerateScoreData {

    private int generateNumber() {
        Random rnd = new Random();
        int randomNum = rnd.nextInt(1000000) + 1;
        return randomNum;
    }

    public void generateName() {
        String name;
        int num;
        try {
            File file = ResourceUtils.getFile("classpath:file/list_names.txt");
            String path = file.getPath();
            System.out.println("The Path: "+ path);

            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((name = reader.readLine()) != null) {
                num = generateNumber();
                sendData(name, num);
                System.out.println("Name: "+name+" :::: Number: "+num);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendData(String name, int num) {
        try {
            Jedis jedis = new Jedis("redis-demo1.cloudapp.net");
            jedis.zadd("highscores", (double)num, name);
            jedis.close();
        } catch(JedisConnectionException e) {
            e.printStackTrace();
        }
    }
}
