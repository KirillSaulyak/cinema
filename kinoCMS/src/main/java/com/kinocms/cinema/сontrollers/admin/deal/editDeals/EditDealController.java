package com.kinocms.cinema.сontrollers.admin.deal.editDeals;

import com.kinocms.cinema.model.DealsEntity;
import com.kinocms.cinema.services.DealsGalleryService;
import com.kinocms.cinema.services.DealsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class EditDealController {
    @Autowired
    private DealsService dealsService;
    @Autowired
    private DealsGalleryService dealsGalleryService;

    @GetMapping("/edit-deal")
    public String showDeal(@RequestParam("idDeal") Integer idDeal, Model model)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        DealsEntity dealsEntity = dealsService.findById(idDeal);

        model.addAttribute("oldDeal", gson.toJson(dealsEntity));
        model.addAttribute("oldImagesFromGallery", gson.toJson(dealsGalleryService.findByDealsByDealFk(dealsEntity)));
        return "admin/deal/EditDeal";
    }
}
