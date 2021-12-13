package com.kinocms.cinema.сontrollers.admin.deal.createDeals;

import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.DealsEntity;
import com.kinocms.cinema.model.DealsGalleriesEntity;
import com.kinocms.cinema.services.DealsGalleryService;
import com.kinocms.cinema.services.DealsService;
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
public class CreateDealRestController {
    @Autowired
    private DealsService dealsService;
    @Autowired
    private DealsGalleryService dealsGalleryService;

    @PostMapping("/create-deal")
    public void createDeal(@RequestParam(name = "dealsInformation") String dealsInformation,
                           @RequestParam(name = "mainImage") MultipartFile mainImage,
                           @RequestParam(name = "dealsGallery") List<MultipartFile> dealsGallery) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
        FileToDisk fileToDisk = new FileToDisk();

        DealsEntity dealsEntity = gson.fromJson(dealsInformation, DealsEntity.class);
        dealsEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        dealsEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.DEALS, dealsEntity.getName(),
                "mainImage", mainImage));

        DealsEntity dealsEntityWithId =  dealsService.save(dealsEntity);

        for (var image : dealsGallery) {
            dealsGalleryService.save(new DealsGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.DEALS,
                    dealsEntity.getName(), UUID.randomUUID().toString(), image), dealsEntityWithId));
        }
    }
}
