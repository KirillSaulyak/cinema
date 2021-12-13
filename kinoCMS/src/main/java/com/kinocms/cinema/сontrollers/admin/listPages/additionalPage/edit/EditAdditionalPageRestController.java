package com.kinocms.cinema.сontrollers.admin.listPages.additionalPage.edit;

import com.kinocms.cinema.filesManipulation.*;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class EditAdditionalPageRestController {
    @Autowired
    private AdditionalPagesService additionalPagesService;
    @Autowired
    private AdditionalPagesGalleriesService additionalPagesGalleriesService;

    @PostMapping("/edit-additional-page")
    public void editAdditionalPage(@RequestParam(name = "additionalPagesInformation") String additionalPagesInformation,
                         @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                        @RequestParam(name = "additionalPagesGallery", required = false) List<MultipartFile> additionalPagesGallery,
                         @RequestParam(name = "deleteOldAdditionalPageImagesFromGallery", required = false) List<Integer> deleteOldAdditionalPageImagesFromGallery) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldAdditionalPageImagesFromGallery != null) {
            for (var id : deleteOldAdditionalPageImagesFromGallery) {
                DeleteFile.delete(additionalPagesGalleriesService.findById(id).getPathToImage());
                additionalPagesGalleriesService.deleteById(id);
            }
        }

        AdditionalPagesEntity newAdditionalPagesEntity = gson.fromJson(additionalPagesInformation, AdditionalPagesEntity.class);
        AdditionalPagesEntity oldAdditionalPagesEntity = additionalPagesService.findById(newAdditionalPagesEntity.getIdAdditionalPage());

        newAdditionalPagesEntity.setCreationDate(oldAdditionalPagesEntity.getCreationDate());
        if (mainImage != null) {
            newAdditionalPagesEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.ADDITIONAL_PAGES, newAdditionalPagesEntity.getName(),
                    "mainImage", mainImage));
        }
        else
        {
            newAdditionalPagesEntity.setPathToMainImage(oldAdditionalPagesEntity.getPathToMainImage());
        }

        if (oldAdditionalPagesEntity != null && !oldAdditionalPagesEntity.getName().contains(newAdditionalPagesEntity.getName())) {
            List<AdditionalPagesGalleriesEntity> oldAdditionalPagesGalleriesEntities =
                    additionalPagesGalleriesService.findByAdditionalPagesByAdditionalPageFk(oldAdditionalPagesEntity);
            String pathToAdditionalPages = "/" + FileToDisk.NAME_ROOT_FOLDER + "/" + TypesFile.IMAGE.getNameFolder() + "/" + TypesGallery.ADDITIONAL_PAGES + "/";
            RenameFile.rename(pathToAdditionalPages + oldAdditionalPagesEntity.getName(), pathToAdditionalPages
                    + newAdditionalPagesEntity.getName());

            for (var oldPath : oldAdditionalPagesGalleriesEntities) {
                oldPath.setPathToImage(pathToAdditionalPages + newAdditionalPagesEntity.getName()
                        + oldPath.getPathToImage().substring(oldPath.getPathToImage().lastIndexOf("/")));
                additionalPagesGalleriesService.save(oldPath);
            }
            newAdditionalPagesEntity.setPathToMainImage(pathToAdditionalPages + newAdditionalPagesEntity.getName()
                    + oldAdditionalPagesEntity.getPathToMainImage()
                    .substring(oldAdditionalPagesEntity.getPathToMainImage().lastIndexOf("/")));
        }

        additionalPagesService.save(newAdditionalPagesEntity);
        if (additionalPagesGallery != null) {
            for (var image : additionalPagesGallery) {
                additionalPagesGalleriesService.save(new AdditionalPagesGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.ADDITIONAL_PAGES,
                        newAdditionalPagesEntity.getName(), UUID.randomUUID().toString(), image), newAdditionalPagesEntity));
            }
        }
    }
}
