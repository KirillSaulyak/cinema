package com.kinocms.cinema.сontrollers.kinocms.generalRequest.templatesRequest;

import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.model.AdditionalPagesGalleriesEntity;
import com.kinocms.cinema.services.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class UserTemplatesRestController {
    @Autowired
    private MainPagesInformationService mainPagesInformationService;
    @Autowired
    private BackgroundBannersService backgroundBannersService;
    @Autowired
    private DescriptionCurrentCinemasService descriptionCurrentCinemasService;
    @Autowired
    private CafesService cafesService;
    @Autowired
    private VipHallsService vipHallsService;
    @Autowired
    private AdvertisementsService advertisementsService;
    @Autowired
    private ChildrensRoomsService childrensRoomsService;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private AdditionalPagesService additionalPagesService;
    @Autowired
    private AdditionalPagesGalleriesService additionalPagesGalleriesService;

    @PostMapping("/send-state-default-pages")
    public Map<String, String> sendStateDefaultPages() {
        Gson gson = new Gson();
        Map<String, String> stateDefaultPages = new HashMap<>();
        stateDefaultPages.put("descriptionCurrentCinema", gson.toJson(descriptionCurrentCinemasService.findFirst().isEnabled()));
        stateDefaultPages.put("cafe", gson.toJson(cafesService.findFirst().isEnabled()));
        stateDefaultPages.put("vipHall", gson.toJson(vipHallsService.findFirst().isEnabled()));
        stateDefaultPages.put("advertisement", gson.toJson(advertisementsService.findFirst().isEnabled()));
        stateDefaultPages.put("childrensRoom", gson.toJson(childrensRoomsService.findFirst().isEnabled()));
        stateDefaultPages.put("contact", gson.toJson(contactsService.findFirst().isEnabled()));

        return stateDefaultPages;
    }

    @PostMapping("/send-additional-pages")
    public Map<String, String> sendStateAdditionalPages() {
        Gson gson = new Gson();
        Map<String, String> stateDefaultPages = new HashMap<>();

        List<AdditionalPagesEntity> additionalPagesEntities = additionalPagesService.findAll();

        List<List<AdditionalPagesGalleriesEntity>> additionalPagesGalleries = new LinkedList<>();
        for (var additionalPage : additionalPagesEntities) {
            additionalPagesGalleries.add(additionalPagesGalleriesService.findByAdditionalPagesByAdditionalPageFk(additionalPage));
        }

        stateDefaultPages.put("additionalPages", gson.toJson(additionalPagesEntities));
        stateDefaultPages.put("additionalPagesGalleries", gson.toJson(additionalPagesGalleries));
        return stateDefaultPages;
    }

    @PostMapping("/send-phone-number")
    public String sendPhoneNumber() {
        Gson gson = new Gson();

        return gson.toJson(mainPagesInformationService.findAll().get(0));
    }

    @PostMapping("/send-background-banner")
    public String sendBackgroundBanner() {
        Gson gson = new Gson();
        return gson.toJson(backgroundBannersService.findFirst());
    }
}
