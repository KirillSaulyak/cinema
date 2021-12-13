package com.kinocms.cinema.сontrollers.admin.listPages.mainPage;

import com.kinocms.cinema.model.MainPagesInformationEntity;
import com.kinocms.cinema.services.MainPagesInformationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class MainPageRestController {
    @Autowired
    private MainPagesInformationService mainPagesInformationService;

    @PostMapping("/edit-default-entry-Главная страница")
    public String sendMainPageInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        String mainPageInformation = gson.toJson(mainPagesInformationService.findAll().get(0));

        return mainPageInformation;
    }

    @PostMapping("/save-default-entry-Главная страница")
    public void saveMainPageInformation(@RequestParam("mainPagesInformation") String mainPagesInformation) {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        MainPagesInformationEntity mainPagesInformationEntity = gson.fromJson(mainPagesInformation, MainPagesInformationEntity.class);
        mainPagesInformationEntity.setIdMainPageInformation(mainPagesInformationEntity.getIdMainPageInformation());

        mainPagesInformationService.save(mainPagesInformationEntity);

    }
}
