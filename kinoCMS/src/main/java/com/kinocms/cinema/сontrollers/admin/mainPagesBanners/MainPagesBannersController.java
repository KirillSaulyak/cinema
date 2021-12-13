package com.kinocms.cinema.сontrollers.admin.mainPagesBanners;

import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MainPagesBannersController {
    @Autowired
    private TopBannersService topBannersService;
    @Autowired
    private TopBannersGalleriesWithInformationService topBannersGalleriesWithInformationService;
    @Autowired
    private BackgroundBannersService backgroundBannersService;
    @Autowired
    private NewsAndDealsBannersService newsAndDealsBannersService;
    @Autowired
    private NewsAndDealsBannersGalleriesWithInformationService newsAndDealsBannersGalleriesWithInformationService;

    @GetMapping("/edit-banners")
    public String createBanners(Model model) {
        Gson jsonObject = new Gson();

        TopBannersEntity topBannersEntity = topBannersService.findById(1);
        if (topBannersEntity != null) {
            List<TopBannersGalleriesWithInformationEntity> topBannersGalleriesWithInformationEntities
                    = topBannersGalleriesWithInformationService.findAll();

            model.addAttribute("oldTopBanners", jsonObject.toJson(topBannersGalleriesWithInformationEntities));
            model.addAttribute("oldTopBannersSetting", jsonObject.toJson(topBannersEntity));
        }

        BackgroundBannersEntity backgroundBannersEntity = backgroundBannersService.findById(1);
        if (backgroundBannersEntity != null) {
            model.addAttribute("backgroundBannerSetting", jsonObject.toJson(backgroundBannersEntity));
        }

        NewsAndDealsBannersEntity newsAndDealsBannersEntity = newsAndDealsBannersService.findById(1);
        if (newsAndDealsBannersEntity != null) {
            List<NewsAndDealsBannersGalleriesWithInformationEntity> newsAndDealsBannersGalleriesWithInformationEntities
                    = newsAndDealsBannersGalleriesWithInformationService.findAll();

            model.addAttribute("oldBottomBanners", jsonObject.toJson(newsAndDealsBannersGalleriesWithInformationEntities));
            model.addAttribute("oldBottomBannersSetting", jsonObject.toJson(newsAndDealsBannersEntity));
        }
        return ("admin/Banners");
    }
}
