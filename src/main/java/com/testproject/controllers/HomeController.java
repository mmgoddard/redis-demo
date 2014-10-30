package com.testproject.controllers;

import com.testproject.models.GenerateArticleData;
import com.testproject.models.GenerateScoreData;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@Component
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    private ModelAndView serveContents(Model model) {
        return new ModelAndView("Contents");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/scores")
    private ModelAndView serveScores(Model model) {
        try {
            Jedis jedis = new Jedis("redis-demo1.cloudapp.net");
            Set<Tuple> elements = jedis.zrevrangeWithScores("highscores", 0, 99);
            jedis.close();
            for (Tuple tuple : elements) {
                System.out.println("Element: " + tuple.getElement() + " :::: Score: " + tuple.getScore());
            }
            model.addAttribute("elements", elements);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return new ModelAndView("Scores");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/articles")
    private ModelAndView serveArticles(Model model) {
        GenerateArticleData.sendData();
        try {
            Jedis jedis = new Jedis("redis-demo1.cloudapp.net");
            List elements = jedis.lrange("articles", 0, 99);
            HashMap<String, Response<String>> values = new HashMap<String, Response<String>>();
            Pipeline pipe = jedis.pipelined();
            for (int i = 0; i < elements.size(); i++) {
                Response<String> element = pipe.hget("hashedArticles", elements.get(i).toString());
                values.put(elements.get(i).toString(), element);
            }
            pipe.sync();
            jedis.close();
            model.addAttribute("values", values);
            model.addAttribute("list", elements);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return new ModelAndView("Articles");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visitors")
    private ModelAndView serveVisitors(Model model) {
        return new ModelAndView("Visitors");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addArticle")
    private ModelAndView addArticle(HttpServletRequest request, HttpServletResponse response, Model model) {
        String articleName = request.getParameter("article");
        System.out.print(articleName);
        try {
            Jedis jedis = new Jedis("redis-demo1.cloudapp.net");
            Pipeline pipe = jedis.pipelined();
            //GenerateArticleData gad = new GenerateArticleData();
            String number = String.valueOf(new DateTime().getMillis());
            pipe.lpush("articles", number);
            pipe.hset("hashedArticles", number, articleName);
            pipe.ltrim("articles", 0, 99);
            pipe.sync();
            jedis.close();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:articles");
    }
}
