package com.kinocms.cinema.сontrollers.kinocms.generalRequest.newsRequest;

import com.kinocms.cinema.model.NewsEntity;
import com.kinocms.cinema.model.NewsGalleriesEntity;
import com.kinocms.cinema.services.NewsService;
import com.kinocms.cinema.services.NewsGalleriesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class UserNewsRestController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsGalleriesService newsGalleriesService;


    @PostMapping("/send-all-news-information")
    public Map<String, String> sendNewsInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        Map<String, String> newsInformation = new HashMap<>();

        List<NewsEntity> newsEntities = newsService.findAll();
        List<List<NewsGalleriesEntity>> newsGalleries = new LinkedList<>();

        for (var deal : newsEntities) {
            newsGalleries.add(newsGalleriesService.findByNewsByNewsFk(deal));
        }
        newsInformation.put("news", gson.toJson(newsEntities));
        newsInformation.put("newsGalleries", gson.toJson(newsGalleries));

        return newsInformation;
    }

    @PostMapping("/send-chosen-news-information")
    public Map<String, String> sendChosenNewsInformation(@RequestParam("idNews") Integer idNews) {
        Gson gson = new Gson();
        Map<String, String> news = new HashMap<>();

        NewsEntity newsEntity = newsService.findById(idNews);
        news.put("deal", gson.toJson(newsEntity));

        List<NewsGalleriesEntity> moviesGallery = newsGalleriesService.findByNewsByNewsFk(newsEntity);
        news.put("newsGallery", gson.toJson(moviesGallery));

        return news;
    }
}
