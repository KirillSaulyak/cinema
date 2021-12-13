package com.kinocms.cinema.сontrollers.admin.cinema.editCinema;

import com.kinocms.cinema.filesManipulation.*;
import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.model.CinemasGalleriesEntity;
import com.kinocms.cinema.model.HallsEntity;
import com.kinocms.cinema.model.HallsGalleriesEntity;
import com.kinocms.cinema.services.CinemasGalleriesService;
import com.kinocms.cinema.services.CinemasService;
import com.kinocms.cinema.services.HallsGalleriesService;
import com.kinocms.cinema.services.HallsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class EditCinemaRestController {
    @Autowired
    private CinemasService cinemasService;
    @Autowired
    private CinemasGalleriesService cinemasGalleriesService;
    @Autowired
    private HallsService hallsService;
    @Autowired
    private HallsGalleriesService hallsGalleriesService;

    @PostMapping("/edit-cinema")
    private void editCinema(@RequestParam(name = "cinemasInformation") String cinemasInformation,
                            @RequestParam(name = "cinemasLogo", required = false) MultipartFile cinemasLogo,
                            @RequestParam(name = "cinemasTopBanner", required = false) MultipartFile cinemasTopBanner,
                            @RequestParam(name = "cinemasGallery", required = false) List<MultipartFile> cinemasGallery,
                            @RequestParam(name = "deleteOldImagesFromGalley", required = false) List<Integer> deleteOldImagesFromGalley) {

        Gson gson = new Gson();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldImagesFromGalley != null) {
            for (var id : deleteOldImagesFromGalley) {
                DeleteFile.delete(cinemasGalleriesService.findById(id).getPathToImage());
                cinemasGalleriesService.deleteById(id);
            }
        }

        CinemasEntity newCinemasEntity = gson.fromJson(cinemasInformation, CinemasEntity.class);
        CinemasEntity oldCinemasEntity = cinemasService.findById(newCinemasEntity.getIdCinema());
        if (oldCinemasEntity != null && !oldCinemasEntity.getName().contains(newCinemasEntity.getName())) {
            List<CinemasGalleriesEntity> oldCinemasGalleriesEntities = cinemasGalleriesService.getByCinemasByCinemaFk(oldCinemasEntity);
            String pathToCinema = "/" + FileToDisk.NAME_ROOT_FOLDER + "/" + TypesFile.IMAGE.getNameFolder() + "/" + TypesGallery.CINEMAS + "/";
            RenameFile.rename(pathToCinema + oldCinemasEntity.getName(), pathToCinema + newCinemasEntity.getName());
            for (var oldPath : oldCinemasGalleriesEntities) {
                oldPath.setPathToImage(pathToCinema + newCinemasEntity.getName()
                        + oldPath.getPathToImage().substring(oldPath.getPathToImage().lastIndexOf("/")));
                cinemasGalleriesService.save(oldPath);
            }
            newCinemasEntity.setPathToLogo(pathToCinema + newCinemasEntity.getName() + oldCinemasEntity
                    .getPathToLogo().substring(oldCinemasEntity.getPathToLogo().lastIndexOf("/")));
            newCinemasEntity.setPathToTopBanner(pathToCinema + newCinemasEntity.getName() + oldCinemasEntity
                    .getPathToTopBanner().substring(oldCinemasEntity.getPathToLogo().lastIndexOf("/")));
        }


        if (cinemasLogo != null) {
            if (oldCinemasEntity != null) {
                DeleteFile.delete(oldCinemasEntity.getPathToLogo());
            }
            newCinemasEntity.setPathToLogo(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CINEMAS, newCinemasEntity.getName(),
                    "logo", cinemasLogo));
        }

        if (cinemasTopBanner != null) {
            if (oldCinemasEntity != null) {
                DeleteFile.delete(oldCinemasEntity.getPathToTopBanner());
            }
            newCinemasEntity.setPathToTopBanner(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CINEMAS, newCinemasEntity.getName(),
                    "topBanner", cinemasTopBanner));
        }

        cinemasService.save(newCinemasEntity);
        if (cinemasGallery != null) {
            for (var image : cinemasGallery) {
                cinemasGalleriesService.save(new CinemasGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CINEMAS,
                        newCinemasEntity.getName(), UUID.randomUUID().toString(), image), newCinemasEntity));
            }
        }
    }

    @DeleteMapping("/delete-cinema")
    private void deleteCinema(@RequestParam(name = "cinemasId") Integer cinemasId) {
        CinemasEntity cinemasEntity = cinemasService.findById(cinemasId);

        String pathToFolder;
        List<HallsEntity> hallsEntities = hallsService.findByCinemasByCinemaFk(cinemasEntity);
        List<HallsGalleriesEntity> hallsGalleriesEntities;
        for (var hall : hallsEntities) {
            pathToFolder = hall.getPathToHallLayout().substring(0, hall.getPathToHallLayout().lastIndexOf("/"));
            hallsGalleriesEntities = hallsGalleriesService.getByHallsByHallFk(hall);

            for (var hallsGallery : hallsGalleriesEntities) {
                DeleteFile.delete(hallsGallery.getPathToImage());
            }

            DeleteFile.delete(hall.getPathToHallLayout());
            DeleteFile.delete(hall.getPathToTopBanner());
            DeleteFile.delete(pathToFolder);

            hallsGalleriesEntities.clear();
        }

        List<CinemasGalleriesEntity> cinemasGalleriesEntities = cinemasGalleriesService.getByCinemasByCinemaFk(cinemasEntity);
        for (var picture : cinemasGalleriesEntities) {
            DeleteFile.delete(picture.getPathToImage());
        }

        DeleteFile.delete(cinemasEntity.getPathToLogo());
        DeleteFile.delete(cinemasEntity.getPathToTopBanner());
        DeleteFile.delete(FileToDisk.NAME_ROOT_FOLDER + "/" + TypesFile.IMAGE.getNameFolder() + "/"
                + TypesGallery.CINEMAS + "/" + cinemasEntity.getName());
        cinemasService.deleteById(cinemasId);
    }
}
