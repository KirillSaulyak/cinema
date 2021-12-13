package com.kinocms.cinema.сontrollers.admin.listPages.childrensRoom;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.ChildrensRoomsEntity;
import com.kinocms.cinema.model.ChildrensRoomsGalleriesEntity;
import com.kinocms.cinema.services.ChildrensRoomsGalleriesService;
import com.kinocms.cinema.services.ChildrensRoomsService;
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
public class ChildrensRoomRestController {
    @Autowired
    private ChildrensRoomsService childrensRoomsService;
    @Autowired
    private ChildrensRoomsGalleriesService childrensRoomsGalleriesService;

    @PostMapping("/edit-default-entry-Детская комната")
    public Map<String, String> sendDescriptionCurrentCinemasInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        ChildrensRoomsEntity childrensRoomsEntity = childrensRoomsService.findAll().get(0);
        Map<String, String> cafesEntities = new HashMap<>();
        cafesEntities.put("childrensRoomsInformation", gson.toJson(childrensRoomsEntity));
        cafesEntities.put("childrensRoomsGallery"
                , gson.toJson(childrensRoomsGalleriesService.findByChildrensRoomsByChildrensRoomFk(childrensRoomsEntity)));

        return cafesEntities;
    }

    @PostMapping("/save-default-entry-Детская комната")
    public void saveDescriptionCurrentCinemasInformation(@RequestParam(name = "childrensRoomsInformation") String childrensRoomsInformation,
                                                         @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                                                         @RequestParam(name = "childrensRoomsGallery", required = false) LinkedList<MultipartFile> childrensRoomsGallery,
                                                         @RequestParam(name = "deleteOldChildrensRoomsImagesFromGallery", required = false) LinkedList<Integer> deleteOldChildrensRoomsImagesFromGallery) {

        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldChildrensRoomsImagesFromGallery != null) {
            for (var id : deleteOldChildrensRoomsImagesFromGallery) {
                DeleteFile.delete(childrensRoomsGalleriesService.findById(id).getPathToImage());
                childrensRoomsGalleriesService.deleteById(id);
            }
        }

        ChildrensRoomsEntity childrensRoomsEntity = gson.fromJson(childrensRoomsInformation, ChildrensRoomsEntity.class);

        String oldPathToMainImage = childrensRoomsService.findFirst().getPathToMainImage();
        if (mainImage != null) {
            if(oldPathToMainImage != null)
            {
                DeleteFile.delete(oldPathToMainImage);
            }
            childrensRoomsEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CHILDRENS_ROOMS, childrensRoomsEntity.getName(),
                    "mainImage", mainImage));
        }

        childrensRoomsService.save(childrensRoomsEntity);

        if (childrensRoomsGallery != null) {
            for (var image : childrensRoomsGallery) {
                childrensRoomsGalleriesService.save(new ChildrensRoomsGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CHILDRENS_ROOMS,
                        childrensRoomsEntity.getName(), UUID.randomUUID().toString(), image), childrensRoomsEntity));
            }
        }

    }
}
