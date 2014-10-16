package com.testproject.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;
import java.util.Set;

@RestController
@Component
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView serveAccount(Model model) {
        //model.addAttribute("name", value);

        try {
            Jedis jedis = new Jedis("redis-demo1.cloudapp.net");
            Set<Tuple> elements = jedis.zrevrangeWithScores("highscores", 0, 100);
            jedis.close();
            for (Tuple tuple : elements) {
                System.out.println("Element: " + tuple.getElement() + " :::: Score: " + tuple.getScore());
            }
            model.addAttribute("elements", elements);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return new ModelAndView("Homepage");
    }
}
