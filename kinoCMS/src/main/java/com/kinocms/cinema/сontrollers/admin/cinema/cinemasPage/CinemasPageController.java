package com.kinocms.cinema.сontrollers.admin.cinema.cinemasPage;

import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.services.CinemasService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CinemasPageController {
    @Autowired
    private CinemasService cinemasService;

    @GetMapping("/cinemas")
    public String showCinemasPage(Model model) {
        Gson jsonObject = new Gson();
        List<CinemasEntity> cinemasEntities = cinemasService.findAll();
        model.addAttribute("linkToCreateCinema","/admin/create-cinema");
        if (cinemasEntities != null) {
            model.addAttribute("cinemas", jsonObject.toJson(cinemasEntities));
        }
        return "admin/cinemas/CinemasPage";
    }
}
