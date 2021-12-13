package com.kinocms.cinema.сontrollers.admin.listPages.cafe;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.CafesEntity;
import com.kinocms.cinema.model.CafesGalleriesEntity;
import com.kinocms.cinema.services.CafesGalleriesService;
import com.kinocms.cinema.services.CafesService;
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
public class CafeRestController {
    @Autowired
    private CafesService cafesService;
    @Autowired
    private CafesGalleriesService cafesGalleriesService;

    @PostMapping("/edit-default-entry-Кафе-бар")
    public Map<String, String> sendCafesInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        CafesEntity cafesEntity = cafesService.findAll().get(0);
        Map<String, String> cafesEntities = new HashMap<>();
        cafesEntities.put("cafesInformation", gson.toJson(cafesEntity));
        cafesEntities.put("cafesGallery"
                , gson.toJson(cafesGalleriesService.findByCafesByCafeFk(cafesEntity)));

        return cafesEntities;
    }

    @PostMapping("/save-default-entry-Кафе-бар")
    public void saveCafesInformation(@RequestParam(name = "cafesInformation") String cafesInformation,
                                                         @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                                                         @RequestParam(name = "cafesGallery", required = false) LinkedList<MultipartFile> cafesGallery,
                                                         @RequestParam(name = "deleteOldCafesImagesFromGallery", required = false) LinkedList<Integer> deleteOldCafesImagesFromGallery) {

        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldCafesImagesFromGallery != null) {
            for (var id : deleteOldCafesImagesFromGallery) {
                DeleteFile.delete(cafesGalleriesService.findById(id).getPathToImage());
                cafesGalleriesService.deleteById(id);
            }
        }

        CafesEntity cafesEntity = gson.fromJson(cafesInformation, CafesEntity.class);

        String oldPathToMainImage = cafesService.findFirst().getPathToMainImage();
        if (mainImage != null) {
            if(oldPathToMainImage != null)
            {
                DeleteFile.delete(oldPathToMainImage);
            }
            cafesEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CAFES, cafesEntity.getName(),
                    "mainImage", mainImage));
        }

        cafesService.save(cafesEntity);

        if (cafesGallery != null) {
            for (var image : cafesGallery) {
                cafesGalleriesService.save(new CafesGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CAFES,
                        cafesEntity.getName(), UUID.randomUUID().toString(), image), cafesEntity));
            }
        }

    }
}
