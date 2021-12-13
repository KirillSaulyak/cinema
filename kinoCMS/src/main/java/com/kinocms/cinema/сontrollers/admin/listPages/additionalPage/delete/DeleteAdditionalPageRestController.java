package com.kinocms.cinema.сontrollers.admin.listPages.additionalPage.delete;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.model.AdditionalPagesGalleriesEntity;
import com.kinocms.cinema.services.AdditionalPagesGalleriesService;
import com.kinocms.cinema.services.AdditionalPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class DeleteAdditionalPageRestController {
    @Autowired
    private AdditionalPagesService additionalPagesService;
    @Autowired
    private AdditionalPagesGalleriesService additionalPagesGalleriesService;

    @DeleteMapping("/delete-additional-page")
    public void deleteAdditionalPage(@RequestParam("idAdditionalPage") Integer idAdditionalPage) {

        AdditionalPagesEntity additionalPagesEntity = additionalPagesService.findById(idAdditionalPage);
        List<AdditionalPagesGalleriesEntity> additionalPagesGalleriesEntity =
                additionalPagesGalleriesService.findByAdditionalPagesByAdditionalPageFk(additionalPagesEntity);

        for (var picture : additionalPagesGalleriesEntity) {
            DeleteFile.delete(picture.getPathToImage());
        }

        DeleteFile.delete(additionalPagesEntity.getPathToMainImage());

        DeleteFile.delete(FileToDisk.NAME_ROOT_FOLDER + "/" +TypesFile.IMAGE.getNameFolder() + "/"
                + TypesGallery.ADDITIONAL_PAGES + "/" + additionalPagesEntity.getName());

        additionalPagesService.deleteById(idAdditionalPage);
    }
}
