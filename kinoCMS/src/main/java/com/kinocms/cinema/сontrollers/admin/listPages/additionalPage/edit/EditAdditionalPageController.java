package com.kinocms.cinema.сontrollers.admin.listPages.additionalPage.edit;

import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.services.AdditionalPagesGalleriesService;
import com.kinocms.cinema.services.AdditionalPagesService;
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
public class EditAdditionalPageController {
    @Autowired
    private AdditionalPagesService additionalPagesService;
    @Autowired
    private AdditionalPagesGalleriesService additionalPagesGalleriesService;

    @GetMapping("/edit-additional-page")
    public String showAdditionalPage(@RequestParam("idAdditionalPage") Integer idAdditionalPage, Model model)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        AdditionalPagesEntity additionalPagesEntity = additionalPagesService.findById(idAdditionalPage);

        model.addAttribute("oldAdditionalPage", gson.toJson(additionalPagesEntity));
        model.addAttribute("oldImagesFromGallery",
                gson.toJson(additionalPagesGalleriesService.findByAdditionalPagesByAdditionalPageFk(additionalPagesEntity)));

        return "admin/pages/additionalPage/EditAdditionalPage";
    }
}
