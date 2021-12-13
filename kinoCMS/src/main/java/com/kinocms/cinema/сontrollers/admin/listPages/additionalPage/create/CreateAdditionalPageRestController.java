package com.kinocms.cinema.сontrollers.admin.listPages.additionalPage.create;

import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.model.AdditionalPagesGalleriesEntity;
import com.kinocms.cinema.services.AdditionalPagesGalleriesService;
import com.kinocms.cinema.services.AdditionalPagesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class CreateAdditionalPageRestController {
    @Autowired
    private AdditionalPagesService additionalPagesService;
    @Autowired
    private AdditionalPagesGalleriesService additionalPagesGalleriesService;

    @PostMapping("/create-additional-page")
    public void createAdditionalPage(@RequestParam("additionalPagesInformation") String additionalPageInformation,
                           @RequestParam("mainImage") MultipartFile mainImage,
                           @RequestParam("additionalPagesGallery") List<MultipartFile> additionalPageGallery) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
        FileToDisk fileToDisk = new FileToDisk();

        AdditionalPagesEntity additionalPagesEntity = gson.fromJson(additionalPageInformation, AdditionalPagesEntity.class);
        additionalPagesEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        additionalPagesEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.ADDITIONAL_PAGES, additionalPagesEntity.getName(),
                "mainImage", mainImage));

        AdditionalPagesEntity additionalPagesEntityWithId =  additionalPagesService.save(additionalPagesEntity);

        for (var image : additionalPageGallery) {
            additionalPagesGalleriesService.save(new AdditionalPagesGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.ADDITIONAL_PAGES,
                    additionalPagesEntity.getName(), UUID.randomUUID().toString(), image), additionalPagesEntityWithId));
        }
    }
}
