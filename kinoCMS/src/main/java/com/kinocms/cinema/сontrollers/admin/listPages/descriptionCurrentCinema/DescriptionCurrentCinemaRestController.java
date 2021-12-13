package com.kinocms.cinema.сontrollers.admin.listPages.descriptionCurrentCinema;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.DescriptionCurrentCinemasEntity;
import com.kinocms.cinema.model.DescriptionCurrentCinemasGalleriesEntity;
import com.kinocms.cinema.services.DescriptionCurrentCinemasGalleriesService;
import com.kinocms.cinema.services.DescriptionCurrentCinemasService;
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
public class DescriptionCurrentCinemaRestController {
    @Autowired
    private DescriptionCurrentCinemasService descriptionCurrentCinemasService;
    @Autowired
    private DescriptionCurrentCinemasGalleriesService descriptionCurrentCinemasGalleriesService;

    @PostMapping("/edit-default-entry-О кинотеатре")
    public Map<String, String> sendDescriptionCurrentCinemasInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        DescriptionCurrentCinemasEntity descriptionCurrentCinemasEntity = descriptionCurrentCinemasService.findAll().get(0);
        Map<String, String> descriptionCurrentCinemas = new HashMap<>();
        descriptionCurrentCinemas.put("descriptionCurrentCinemasInformation", gson.toJson(descriptionCurrentCinemasEntity));
        descriptionCurrentCinemas.put("descriptionCurrentCinemasGallery"
                , gson.toJson(descriptionCurrentCinemasGalleriesService
                        .findByDescriptionCurrentCinemasByDescriptionCurrentCinemaFk(descriptionCurrentCinemasEntity)));

        return descriptionCurrentCinemas;
    }

    @PostMapping("/save-default-entry-О кинотеатре")
    public void saveDescriptionCurrentCinemasInformation(@RequestParam(name = "descriptionCurrentCinemasInformation") String descriptionCurrentCinemasInformation,
                                                         @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                                                         @RequestParam(name = "descriptionCurrentCinemasGallery", required = false) LinkedList<MultipartFile> descriptionCurrentCinemasGallery,
                                                         @RequestParam(name = "deleteOldDescriptionCurrentCinemaImagesFromGallery", required = false) LinkedList<Integer> deleteOldDescriptionCurrentCinemaImagesFromGallery) {

        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldDescriptionCurrentCinemaImagesFromGallery != null) {
            for (var id : deleteOldDescriptionCurrentCinemaImagesFromGallery) {
                DeleteFile.delete(descriptionCurrentCinemasGalleriesService.findById(id).getPathToImage());
                descriptionCurrentCinemasGalleriesService.deleteById(id);
            }
        }

        DescriptionCurrentCinemasEntity descriptionCurrentCinemasEntity = gson.fromJson(descriptionCurrentCinemasInformation, DescriptionCurrentCinemasEntity.class);

        String oldPathToMainImage = descriptionCurrentCinemasService.findFirst().getPathToMainImage();
        if (mainImage != null) {
            if(oldPathToMainImage != null)
            {
                DeleteFile.delete(oldPathToMainImage);
            }
            descriptionCurrentCinemasEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.DESCRIPTION_CURRENT_CINEMAS_GALLERIES, descriptionCurrentCinemasEntity.getName(),
                    "mainImage", mainImage));
        }

        descriptionCurrentCinemasService.save(descriptionCurrentCinemasEntity);

        if (descriptionCurrentCinemasGallery != null) {
            for (var image : descriptionCurrentCinemasGallery) {
                descriptionCurrentCinemasGalleriesService.save(new DescriptionCurrentCinemasGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.DESCRIPTION_CURRENT_CINEMAS_GALLERIES,
                        descriptionCurrentCinemasEntity.getName(), UUID.randomUUID().toString(), image), descriptionCurrentCinemasEntity));
            }
        }

    }
}
