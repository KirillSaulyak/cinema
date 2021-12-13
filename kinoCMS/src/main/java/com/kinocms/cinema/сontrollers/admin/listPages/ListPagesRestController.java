package com.kinocms.cinema.сontrollers.admin.listPages;

import com.kinocms.cinema.services.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class ListPagesRestController {
    @Autowired
    private MainPagesInformationService mainPagesInformationService;
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

    @PostMapping("/list-pages")
    public Map<String, LinkedList<String>> sendAllPages() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        LinkedList<String> defaultEntries = new LinkedList<>();
        defaultEntries.addLast(gson.toJson(mainPagesInformationService.findAll().get(0)));
        defaultEntries.addLast(gson.toJson(descriptionCurrentCinemasService.findAll().get(0)));
        defaultEntries.addLast(gson.toJson(cafesService.findAll().get(0)));
        defaultEntries.addLast(gson.toJson(vipHallsService.findAll().get(0)));
        defaultEntries.addLast(gson.toJson(advertisementsService.findAll().get(0)));
        defaultEntries.addLast(gson.toJson(childrensRoomsService.findAll().get(0)));
        defaultEntries.addLast(gson.toJson(contactsService.findAll().get(0)));
        Map<String, LinkedList<String>> entriesToListPage = new HashMap<>();
        entriesToListPage.put("defaultEntries", defaultEntries);


        LinkedList<String> additionalEntries = new LinkedList<>();
        additionalEntries.add(gson.toJson(additionalPagesService.findAll()));
        entriesToListPage.put("additionalEntries", additionalEntries);

        return entriesToListPage;
    }
}
