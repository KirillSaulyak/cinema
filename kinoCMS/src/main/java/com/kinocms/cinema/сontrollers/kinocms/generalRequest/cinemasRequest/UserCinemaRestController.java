package com.kinocms.cinema.сontrollers.kinocms.generalRequest.cinemasRequest;

import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.CinemasGalleriesService;
import com.kinocms.cinema.services.CinemasService;
import com.kinocms.cinema.services.HallsService;
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
public class UserCinemaRestController {
    @Autowired
    private CinemasService cinemasService;
    @Autowired
    private CinemasGalleriesService cinemasGalleriesService;
    @Autowired
    private HallsService hallsService;

    @PostMapping("/send-all-cinemas-information")
    public Map<String, String> sendCinemasInformation() {
        Gson gson = new Gson();
        Map<String, String> cinemasInformation = new HashMap<>();
        List<CinemasEntity> cinemasEntities = cinemasService.findAll();
        List<List<CinemasGalleriesEntity>> cinemasGalleries = new LinkedList<>();
        List<List<HallsEntity>> halls = new LinkedList<>();

        for (var cinema : cinemasEntities) {
            cinemasGalleries.add(cinemasGalleriesService.getByCinemasByCinemaFk(cinema));
            halls.add(hallsService.findByCinemasByCinemaFk(cinema));
        }
        cinemasInformation.put("cinemas", gson.toJson(cinemasEntities));
        cinemasInformation.put("cinemasGalleries", gson.toJson(cinemasGalleries));
        cinemasInformation.put("cinemasHalls", gson.toJson(halls));

        return cinemasInformation;
    }

    @PostMapping("/send-chosen-cinema-information")
    public Map<String, String> sendChosenCinemaInformation(@RequestParam("idCinema") Integer idCinema) {
        Gson gson = new Gson();
        Map<String, String> cinemasInformation = new HashMap<>();
        CinemasEntity cinemasEntity = cinemasService.findById(idCinema);
        List<CinemasGalleriesEntity> cinemasGallery = cinemasGalleriesService.getByCinemasByCinemaFk(cinemasEntity);
        List<HallsEntity> halls = hallsService.findByCinemasByCinemaFk(cinemasEntity);

        cinemasInformation.put("cinema", gson.toJson(cinemasEntity));
        cinemasInformation.put("cinemasGallery", gson.toJson(cinemasGallery));
        cinemasInformation.put("cinemasHalls", gson.toJson(halls));

        return cinemasInformation;
    }
}
