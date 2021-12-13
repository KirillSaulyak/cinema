package com.kinocms.cinema.сontrollers.kinocms.generalRequest.dealsRequest;

import com.kinocms.cinema.model.DealsEntity;
import com.kinocms.cinema.model.DealsGalleriesEntity;
import com.kinocms.cinema.services.DealsGalleryService;
import com.kinocms.cinema.services.DealsService;
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
public class UserDealsRestController {
    @Autowired
    private DealsService dealsService;
    @Autowired
    private DealsGalleryService dealsGalleryService;


    @PostMapping("/send-all-deals-information")
    public Map<String, String> sendDealsInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        Map<String, String> dealsInformation = new HashMap<>();

        List<DealsEntity> dealsEntities = dealsService.findAll();
        List<List<DealsGalleriesEntity>> dealsGalleries = new LinkedList<>();

        for (var deal : dealsEntities) {
            dealsGalleries.add(dealsGalleryService.findByDealsByDealFk(deal));
        }
        dealsInformation.put("deals", gson.toJson(dealsEntities));
        dealsInformation.put("dealsGalleries", gson.toJson(dealsGalleries));

        return dealsInformation;
    }

    @PostMapping("/send-chosen-deal-information")
    public Map<String, String> sendChosenDealInformation(@RequestParam("idDeal") Integer idDeal) {
        Gson gson = new Gson();
        Map<String, String> deals = new HashMap<>();

        DealsEntity dealsEntity = dealsService.findById(idDeal);
        deals.put("deal", gson.toJson(dealsEntity));

        List<DealsGalleriesEntity> moviesGallery = dealsGalleryService.findByDealsByDealFk(dealsEntity);
        deals.put("dealsGallery", gson.toJson(moviesGallery));

        return deals;
    }
}
