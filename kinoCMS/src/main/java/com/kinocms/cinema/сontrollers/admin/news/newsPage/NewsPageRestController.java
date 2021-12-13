package com.kinocms.cinema.сontrollers.admin.news.newsPage;

import com.kinocms.cinema.services.NewsService;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class NewsPageRestController {
    @Autowired
    private NewsService newsService;
    @PostMapping("/news")
    public String sendAllNews()
    {
        return new GsonBuilder().setDateFormat("dd.MM.yyyy").create().toJson(newsService.findAll());
    }
}
