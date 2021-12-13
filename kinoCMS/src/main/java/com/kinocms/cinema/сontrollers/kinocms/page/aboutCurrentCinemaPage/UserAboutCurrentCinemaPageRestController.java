package com.kinocms.cinema.сontrollers.kinocms.page.aboutCurrentCinemaPage;

import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.DescriptionCurrentCinemasGalleriesService;
import com.kinocms.cinema.services.DescriptionCurrentCinemasService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserAboutCurrentCinemaPageRestController {
    @Autowired
    private DescriptionCurrentCinemasService descriptionCurrentCinemasService;
    @Autowired
    private DescriptionCurrentCinemasGalleriesService descriptionCurrentCinemasGalleriesService;

    @PostMapping("/send-current-cinema-information")
    public Map<String, String> sendCurrentCinemaInformation() {
        Gson gson = new Gson();
        Map<String, String> cinemasInformation = new HashMap<>();
        DescriptionCurrentCinemasEntity descriptionCurrentCinemasEntity = descriptionCurrentCinemasService.findFirst();

        List<DescriptionCurrentCinemasGalleriesEntity> descriptionCurrentCinemasGallery = descriptionCurrentCinemasGalleriesService
                .findByDescriptionCurrentCinemasByDescriptionCurrentCinemaFk(descriptionCurrentCinemasEntity);

        cinemasInformation.put("descriptionCurrentCinema", gson.toJson(descriptionCurrentCinemasEntity));
        cinemasInformation.put("descriptionCurrentCinemasGallery", gson.toJson(descriptionCurrentCinemasGallery));


        return cinemasInformation;
    }
}
