package com.kinocms.cinema.сontrollers.admin.deal.editDeals;

import com.kinocms.cinema.filesManipulation.*;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class EditDealRestController {
    @Autowired
    private DealsService dealsService;
    @Autowired
    private DealsGalleryService dealsGalleryService;

    @PostMapping("/edit-deal")
    public void editDeal(@RequestParam(name = "dealsInformation") String dealsInformation,
                         @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                         @RequestParam(name = "dealsGallery", required = false) List<MultipartFile> dealsGallery,
                         @RequestParam(name = "deleteOldDealsImagesFromGallery", required = false) List<Integer> deleteOldDealsImagesFromGallery) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldDealsImagesFromGallery != null) {
            for (var id : deleteOldDealsImagesFromGallery) {
                DeleteFile.delete(dealsGalleryService.findById(id).getPathToImage());
                dealsGalleryService.deleteById(id);
            }
        }

        DealsEntity newDealsEntity = gson.fromJson(dealsInformation, DealsEntity.class);
        DealsEntity oldDealsEntity = dealsService.findById(newDealsEntity.getIdDeal());

        newDealsEntity.setCreationDate(oldDealsEntity.getCreationDate());

        String oldPathToMainImage = oldDealsEntity.getPathToMainImage();
        if (mainImage != null) {
            if (oldPathToMainImage != null) {
                DeleteFile.delete(oldPathToMainImage);
            }
            newDealsEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.DEALS, newDealsEntity.getName(),
                    "mainImage", mainImage));
        }
        else
        {
            newDealsEntity.setPathToMainImage(oldDealsEntity.getPathToMainImage());
        }

        if (oldDealsEntity != null && !oldDealsEntity.getName().contains(newDealsEntity.getName())) {
            List<DealsGalleriesEntity> oldDealsGalleriesEntities = dealsGalleryService.findByDealsByDealFk(oldDealsEntity);
            String pathToDeals = "/" + FileToDisk.NAME_ROOT_FOLDER + "/" + TypesFile.IMAGE.getNameFolder() + "/" + TypesGallery.DEALS + "/";
            RenameFile.rename(pathToDeals + oldDealsEntity.getName(), pathToDeals + newDealsEntity.getName());
            for (var oldPath : oldDealsGalleriesEntities) {
                oldPath.setPathToImage(pathToDeals + newDealsEntity.getName()
                        + oldPath.getPathToImage().substring(oldPath.getPathToImage().lastIndexOf("/")));
                dealsGalleryService.save(oldPath);
            }
            newDealsEntity.setPathToMainImage(pathToDeals + newDealsEntity.getName() + oldDealsEntity
                    .getPathToMainImage().substring(oldDealsEntity.getPathToMainImage().lastIndexOf("/")));
        }

        dealsService.save(newDealsEntity);
        if (dealsGallery != null) {
            for (var image : dealsGallery) {
                dealsGalleryService.save(new DealsGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.DEALS,
                        newDealsEntity.getName(), UUID.randomUUID().toString(), image), newDealsEntity));
            }
        }
    }
}
