package com.kinocms.cinema.сontrollers.admin.cinema.editCinema;

import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.model.HallsEntity;
import com.kinocms.cinema.model.HallsGalleriesEntity;
import com.kinocms.cinema.services.CinemasGalleriesService;
import com.kinocms.cinema.services.CinemasService;
import com.kinocms.cinema.services.HallsGalleriesService;
import com.kinocms.cinema.services.HallsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class EditCinemaController {
    @Autowired
    private CinemasService cinemasService;
    @Autowired
    private CinemasGalleriesService cinemasGalleriesService;
    @Autowired
    private HallsService hallsService;
    @Autowired
    private HallsGalleriesService hallsGalleriesService;


    @GetMapping("/edit-cinema")
    public String openEditsPage(@RequestParam("idCinema") Integer idCinema, Model model) {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        CinemasEntity cinemasEntity = cinemasService.findById(idCinema);
        List<HallsEntity> hallsEntities = hallsService.findByCinemasByCinemaFk(cinemasEntity);
        model.addAttribute("cinema", gson.toJson(cinemasEntity));
        model.addAttribute("halls", gson.toJson(hallsEntities));
        LinkedList<List<HallsGalleriesEntity>> hallsGalleriesEntities = new LinkedList<>();
        for (var hall : hallsEntities) {
            hallsGalleriesEntities.add(hallsGalleriesService.getByHallsByHallFk(hall));
        }
        model.addAttribute("hallsImagesFromGallery", gson.toJson(hallsGalleriesEntities));
        model.addAttribute("imagesFromGallery", gson.toJson(cinemasGalleriesService.getByCinemasByCinemaFk(cinemasEntity)));
        return "admin/cinemas/EditCinema";
    }
}
