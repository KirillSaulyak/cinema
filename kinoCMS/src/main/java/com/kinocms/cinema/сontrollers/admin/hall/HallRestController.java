package com.kinocms.cinema.сontrollers.admin.hall;

import com.kinocms.cinema.filesManipulation.*;
import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.model.HallsEntity;
import com.kinocms.cinema.model.HallsGalleriesEntity;
import com.kinocms.cinema.services.CinemasService;
import com.kinocms.cinema.services.HallsGalleriesService;
import com.kinocms.cinema.services.HallsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class HallRestController {
    @Autowired
    private CinemasService cinemasService;
    @Autowired
    private HallsService hallsService;
    @Autowired
    private HallsGalleriesService hallsGalleriesService;

    @PostMapping("/edit-hall")
    public Map<String, String> editHall(@RequestParam(name = "cinemaId") Integer cinemaId,
                                        @RequestParam(name = "idHall", required = false) Integer idHall,
                                        @RequestParam(name = "hallsInformation") String hallsInformation,
                                        @RequestParam(name = "hallsLayot", required = false) MultipartFile hallsLayot,
                                        @RequestParam(name = "hallsTopBanner", required = false) MultipartFile hallsTopBanner,
                                        @RequestParam(name = "hallsGallery", required = false) List<MultipartFile> hallsGallery,
                                        @RequestParam(name = "idDeleteOldImagesFromGalley", required = false) List<Integer> deleteOldImagesFromGalley) {

        Gson jsonObject = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        FileToDisk fileToDisk = new FileToDisk();
        CinemasEntity cinemasEntity = cinemasService.findById(cinemaId);
        String nameCurrentHallsFolder = UUID.randomUUID().toString();

        if (deleteOldImagesFromGalley != null) {
            for (var id : deleteOldImagesFromGalley) {
                DeleteFile.delete(hallsGalleriesService.findById(id).getPathToImage());
                hallsGalleriesService.deleteById(id);
            }
        }

        HallsEntity newHallsEntity = jsonObject.fromJson(hallsInformation, HallsEntity.class);
        newHallsEntity.setCinemasByCinemaFk(cinemasEntity);
        newHallsEntity.setIdHall(idHall);

        if (newHallsEntity.getCreationDate() == null) {
            newHallsEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        }
        HallsEntity oldHallsEntity = hallsService.findById(idHall);


        if (hallsLayot != null) {
            if (oldHallsEntity != null) {
                DeleteFile.delete(oldHallsEntity.getPathToHallLayout());
            }
            newHallsEntity.setPathToHallLayout(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.HALLS, nameCurrentHallsFolder,
                    "Layout", hallsLayot));
        } else {
            newHallsEntity.setPathToHallLayout(oldHallsEntity.getPathToHallLayout());
        }

        if (hallsTopBanner != null) {
            if (oldHallsEntity != null) {
                DeleteFile.delete(oldHallsEntity.getPathToTopBanner());
            }
            newHallsEntity.setPathToTopBanner(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.HALLS, nameCurrentHallsFolder,
                    "topBanner", hallsTopBanner));
        } else {
            newHallsEntity.setPathToTopBanner(oldHallsEntity.getPathToTopBanner());
        }

        newHallsEntity = hallsService.save(newHallsEntity);

        LinkedList<HallsGalleriesEntity> hallsGalleriesEntities = new LinkedList<>();
        if (hallsGallery != null) {
            for (var image : hallsGallery) {
                hallsGalleriesEntities.add(hallsGalleriesService.save(new HallsGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.HALLS,
                        nameCurrentHallsFolder, UUID.randomUUID().toString(), image), newHallsEntity)));
            }
        }


        Map<String, String> lists = new HashMap<>();
        lists.put("Cinema", jsonObject.toJson(cinemasEntity));
        lists.put("Hals", jsonObject.toJson(newHallsEntity));
        lists.put("HallsGalleries", jsonObject.toJson(hallsGalleriesEntities));

        return lists;
    }

    @DeleteMapping("/delete-hall")
    public void editHall(@RequestParam(name = "idDeleteHall") Integer idDeleteHall) {
        HallsEntity hallsEntity = hallsService.findById(idDeleteHall);

        List<HallsGalleriesEntity> hallsGalleriesEntities = hallsGalleriesService.getByHallsByHallFk(hallsEntity);

        String pathToFolder = hallsEntity.getPathToHallLayout().substring(0, hallsEntity.getPathToHallLayout().lastIndexOf("/"));

        for (var picture : hallsGalleriesEntities) {
            DeleteFile.delete(picture.getPathToImage());
        }

        DeleteFile.delete(hallsEntity.getPathToHallLayout());
        DeleteFile.delete(hallsEntity.getPathToTopBanner());
        DeleteFile.delete(pathToFolder);

        hallsService.deleteById(idDeleteHall);
    }
}
