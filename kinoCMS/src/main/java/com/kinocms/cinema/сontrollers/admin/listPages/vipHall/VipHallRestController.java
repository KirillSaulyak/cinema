package com.kinocms.cinema.сontrollers.admin.listPages.vipHall;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.VipHallsEntity;
import com.kinocms.cinema.model.VipHallsGalleriesEntity;
import com.kinocms.cinema.services.VipHallsGalleriesService;
import com.kinocms.cinema.services.VipHallsService;
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
public class VipHallRestController {
    @Autowired
    private VipHallsService vipHallsService;
    @Autowired
    private VipHallsGalleriesService vipHallsGalleriesService;

    @PostMapping("/edit-default-entry-Vip-зал")
    public Map<String, String> sendDescriptionCurrentCinemasInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        VipHallsEntity vipHallsEntity = vipHallsService.findAll().get(0);
        Map<String, String> cafesEntities = new HashMap<>();
        cafesEntities.put("vipHallsInformation", gson.toJson(vipHallsEntity));
        cafesEntities.put("vipHallsGallery", gson.toJson(vipHallsGalleriesService.findByVipHallsByVipHallFk(vipHallsEntity)));

        return cafesEntities;
    }

    @PostMapping("/save-default-entry-Vip-зал")
    public void saveDescriptionCurrentCinemasInformation(@RequestParam(name = "vipHallsInformation") String vipHallsInformation,
                                                         @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                                                         @RequestParam(name = "vipHallsGallery", required = false) LinkedList<MultipartFile> vipHallsGallery,
                                                         @RequestParam(name = "deleteOldVipHallsImagesFromGallery", required = false) LinkedList<Integer> deleteOldVipHallsImagesFromGallery) {

        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldVipHallsImagesFromGallery != null) {
            for (var id : deleteOldVipHallsImagesFromGallery) {
                DeleteFile.delete(vipHallsGalleriesService.findById(id).getPathToImage());
                vipHallsGalleriesService.deleteById(id);
            }
        }

        VipHallsEntity vipHallsEntity = gson.fromJson(vipHallsInformation, VipHallsEntity.class);

        String oldPathToMainImage = vipHallsService.findFirst().getPathToMainImage();
        if (mainImage != null) {
            if (oldPathToMainImage != null) {
                DeleteFile.delete(oldPathToMainImage);
            }
            vipHallsEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.VIP_HALLS, vipHallsEntity.getName(),
                    "mainImage", mainImage));
        }

        vipHallsService.save(vipHallsEntity);

        if (vipHallsGallery != null) {
            for (var image : vipHallsGallery) {
                vipHallsGalleriesService.save(new VipHallsGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.VIP_HALLS,
                        vipHallsEntity.getName(), UUID.randomUUID().toString(), image), vipHallsEntity));
            }
        }

    }
}
