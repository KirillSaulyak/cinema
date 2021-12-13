package com.kinocms.cinema.сontrollers.kinocms.page.cafePage;

import com.kinocms.cinema.model.CafesEntity;
import com.kinocms.cinema.model.CafesGalleriesEntity;
import com.kinocms.cinema.services.CafesGalleriesService;
import com.kinocms.cinema.services.CafesService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserCafePageRestController {
    @Autowired
    private CafesService cafesService;
    @Autowired
    private CafesGalleriesService cafesGalleriesService;

    @PostMapping("/send-cafe-information")
    public Map<String, String> sendCafeInformation() {
        Gson gson = new Gson();
        Map<String, String> cafesInformation = new HashMap<>();

        CafesEntity cafesEntity = cafesService.findFirst();
        cafesInformation.put("cafe", gson.toJson(cafesEntity));

        List<CafesGalleriesEntity> moviesGallery = cafesGalleriesService.findByCafesByCafeFk(cafesEntity);
        cafesInformation.put("cafesGallery", gson.toJson(moviesGallery));

        return cafesInformation;
    }
}
