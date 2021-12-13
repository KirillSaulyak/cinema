package com.kinocms.cinema.сontrollers.kinocms.page.mainPage;

import com.kinocms.cinema.services.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserMainPageRestController {
    @Autowired
    private TopBannersService topBannersService;
    @Autowired
    private TopBannersGalleriesWithInformationService topBannersGalleriesWithInformationService;
    @Autowired
    private NewsAndDealsBannersService newsAndDealsBannersService;
    @Autowired
    private NewsAndDealsBannersGalleriesWithInformationService newsAndDealsBannersGalleriesWithInformationService;



    @PostMapping("/send-top-banners-information")
    public Map<String, String> sendTopBannersInformation() {
        Gson gson = new Gson();
        Map<String, String> topBanners = new HashMap<>();
        topBanners.put("setting", gson.toJson(topBannersService.findFirst()));
        topBanners.put("galleriesWithInformation", gson.toJson(topBannersGalleriesWithInformationService.findAll()));
        return topBanners;
    }

    @PostMapping("/send-bottom-banners-information")
    public Map<String, String> sendBottomBannersInformation() {
        Gson gson = new Gson();
        Map<String, String> topBanners = new HashMap<>();
        topBanners.put("setting", gson.toJson(newsAndDealsBannersService.findFirst()));
        topBanners.put("galleriesWithInformation", gson.toJson(newsAndDealsBannersGalleriesWithInformationService.findAll()));
        return topBanners;
    }




}
