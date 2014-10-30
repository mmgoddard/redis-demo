package com.testproject.models;

import org.joda.time.DateTime;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by Mark on 30/10/14.
 */
public class GenerateArticleData {


    private static String generateTitle(int i) {
        String title = "Article" + String.valueOf(i);
        return title;
    }

    public static void sendData() {
        try {
            Jedis jedis = new Jedis("redis-demo1.cloudapp.net");
            Pipeline pipe = jedis.pipelined();

            for(int i = 0; i < 99; i++) {
                DateTime date = new DateTime();
                date = date.plusMinutes(i);
                String number = String.valueOf(date.getMillis());
                pipe.lpush("articles", number);
                pipe.hset("hashedArticles", number, generateTitle(i));
                pipe.ltrim("articles", 0, 99);
            }
            pipe.sync();
            jedis.close();
        } catch(JedisConnectionException e) {
            e.printStackTrace();
        }
    }
}
