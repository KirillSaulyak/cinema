package com.kinocms.cinema.сontrollers.admin.deal.deleteDeals;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.DealsEntity;
import com.kinocms.cinema.model.DealsGalleriesEntity;
import com.kinocms.cinema.services.DealsGalleryService;
import com.kinocms.cinema.services.DealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class DeleteDealRestController {
    @Autowired
    private DealsService dealsService;
    @Autowired
    private DealsGalleryService dealsGalleryService;

    @DeleteMapping("/delete-deal")
    public void deleteDeal(@RequestParam("idDeal") Integer idDeal) {

        DealsEntity dealsEntity = dealsService.findById(idDeal);
        List<DealsGalleriesEntity> dealsGalleriesEntities =  dealsGalleryService.findByDealsByDealFk(dealsEntity);
        for (var picture : dealsGalleriesEntities) {
            DeleteFile.delete(picture.getPathToImage());
        }

        DeleteFile.delete(dealsEntity.getPathToMainImage());

        DeleteFile.delete(FileToDisk.NAME_ROOT_FOLDER + "/" +TypesFile.IMAGE.getNameFolder() + "/"
                + TypesGallery.DEALS + "/" + dealsEntity.getName());

        dealsService.deleteById(idDeal);
    }
}
