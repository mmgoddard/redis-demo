package com.testproject.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@RestController
@Component
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView serveAccount(Model model) {
        String value;
        try {
            Jedis jedis = new Jedis("localhost");
            jedis.set("Mark", "Goddard");
            value = jedis.get("Mark");
            model.addAttribute("name", value);
        } catch(JedisConnectionException e) {
            model.addAttribute("name", "Cannot connect to the redis database.");
            e.printStackTrace();
        }
        return new ModelAndView("Homepage");
    }
}
