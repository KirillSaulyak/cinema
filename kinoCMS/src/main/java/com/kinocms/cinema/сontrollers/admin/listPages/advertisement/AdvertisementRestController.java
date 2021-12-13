package com.kinocms.cinema.сontrollers.admin.listPages.advertisement;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.AdvertisementsEntity;
import com.kinocms.cinema.model.AdvertisementsGalleriesEntity;
import com.kinocms.cinema.services.AdvertisementsGalleriesService;
import com.kinocms.cinema.services.AdvertisementsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdvertisementRestController {
    @Autowired
    private AdvertisementsService advertisementsService;
    @Autowired
    private AdvertisementsGalleriesService advertisementsGalleriesService;

    @PostMapping("/edit-default-entry-Реклама")
    public Map<String, String> sendAdvertisementInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        AdvertisementsEntity advertisementsEntity = advertisementsService.findAll().get(0);
        Map<String, String> advertisementsEntities = new HashMap<>();
        advertisementsEntities.put("advertisementsInformation", gson.toJson(advertisementsEntity));
        advertisementsEntities.put("advertisementsGallery"
                , gson.toJson(advertisementsGalleriesService.findByAdvertisementsByAdvertisementFk(advertisementsEntity)));

        return advertisementsEntities;
    }

    @PostMapping("/save-default-entry-Реклама")
    public void saveAdvertisementInformation(@RequestParam(name = "advertisementsInformation") String advertisementsInformation,
                                             @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                                             @RequestParam(name = "advertisementsGallery", required = false) LinkedList<MultipartFile> advertisementsGallery,
                                             @RequestParam(name = "deleteOldAdvertisementsImagesFromGallery", required = false) LinkedList<Integer> deleteOldAdvertisementsImagesFromGallery) {

        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldAdvertisementsImagesFromGallery != null) {
            for (var id : deleteOldAdvertisementsImagesFromGallery) {
                DeleteFile.delete(advertisementsGalleriesService.findById(id).getPathToImage());
                advertisementsGalleriesService.deleteById(id);
            }
        }

        AdvertisementsEntity advertisementsEntity = gson.fromJson(advertisementsInformation, AdvertisementsEntity.class);

        if (mainImage != null) {
            advertisementsEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.ADVERTISEMENTS, advertisementsEntity.getName(),
                    "mainImage", mainImage));
        }

        advertisementsService.save(advertisementsEntity);

        if (advertisementsGallery != null) {
            for (var image : advertisementsGallery) {
                advertisementsGalleriesService.save(new AdvertisementsGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.ADVERTISEMENTS,
                        advertisementsEntity.getName(), UUID.randomUUID().toString(), image), advertisementsEntity));
            }
        }

    }
}
