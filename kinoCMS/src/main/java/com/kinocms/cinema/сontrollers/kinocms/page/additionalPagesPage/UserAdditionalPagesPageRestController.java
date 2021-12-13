package com.kinocms.cinema.сontrollers.kinocms.page.additionalPagesPage;

import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.model.AdditionalPagesGalleriesEntity;
import com.kinocms.cinema.services.AdditionalPagesGalleriesService;
import com.kinocms.cinema.services.AdditionalPagesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class UserAdditionalPagesPageRestController {
    @Autowired
    private AdditionalPagesService additionalPagesService;
    @Autowired
    private AdditionalPagesGalleriesService additionalPagesGalleriesService;


    @PostMapping("/send-all-additionalPages-information")
    public Map<String, String> sendAdditionalPagesInformation() {
        Gson gson = new Gson();
        Map<String, String> additionalPagesInformation = new HashMap<>();

        List<AdditionalPagesEntity> additionalPagesEntities = additionalPagesService.findAll();
        List<List<AdditionalPagesGalleriesEntity>> additionalPagesGalleries = new LinkedList<>();

        for (var additionalPage : additionalPagesEntities) {
            additionalPagesGalleries.add(additionalPagesGalleriesService.findByAdditionalPagesByAdditionalPageFk(additionalPage));
        }
        additionalPagesInformation.put("additionalPages", gson.toJson(additionalPagesEntities));
        additionalPagesInformation.put("additionalPagesGalleries", gson.toJson(additionalPagesGalleries));

        return additionalPagesInformation;
    }

    @PostMapping("/send-chosen-additional-page-information")
    public Map<String, String> sendChosenAdditionalPageInformation(@RequestParam("idAdditionalPage") Integer idAdditionalPage) {
        Gson gson = new Gson();
        Map<String, String> additionalPages = new HashMap<>();

        AdditionalPagesEntity additionalPagesEntity = additionalPagesService.findById(idAdditionalPage);
        additionalPages.put("additionalPage", gson.toJson(additionalPagesEntity));

        List<AdditionalPagesGalleriesEntity> moviesGallery = additionalPagesGalleriesService.findByAdditionalPagesByAdditionalPageFk(additionalPagesEntity);
        additionalPages.put("additionalPagesGallery", gson.toJson(moviesGallery));

        return additionalPages;
    }
}
