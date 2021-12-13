package com.kinocms.cinema.сontrollers.admin.movie.updateMovies;

import com.kinocms.cinema.filesManipulation.*;
import com.kinocms.cinema.model.MoviesEntity;
import com.kinocms.cinema.model.MoviesGalleriesEntity;
import com.kinocms.cinema.model.MoviesTypesEntity;
import com.kinocms.cinema.services.MoviesGalleriesService;
import com.kinocms.cinema.services.MoviesService;
import com.kinocms.cinema.services.MoviesTypeService;
import com.kinocms.cinema.сontrollers.admin.movie.createMoviesPage.CreateMoviesRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class UpdateMoviesRestController {
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private MoviesGalleriesService moviesGalleriesService;
    @Autowired
    private MoviesTypeService moviesTypeService;

    @PostMapping("/update-movie")
    public void updateMoviesInformation(@RequestParam(name = "moviesId") Integer moviesId,
                                        @RequestParam(name = "moviesName") String moviesName,
                                        @RequestParam(name = "moviesType") ArrayList<String> newMoviesTypes,
                                        @RequestParam(name = "moviesDescription") String moviesDescription,
                                        @RequestParam(name = "uploadMainImg", required = false) MultipartFile mainImage,
                                        @RequestParam(name = "trailerLink") String trailerLink,
                                        @RequestParam(name = "addToGalleryPicture", required = false) MultipartFile[] galleryPictures,
                                        @RequestParam(name = "oldImagesGallery", required = false) ArrayList<String> deleteOldImagesGallery) {
        FileToDisk fileToDisk = new FileToDisk();
        MoviesEntity currentMoviesEntity = moviesService.findById(moviesId);

        if (deleteOldImagesGallery != null) {
            for (var deleteImage : deleteOldImagesGallery) {
                DeleteFile.delete(deleteImage);
                moviesGalleriesService.deleteByPath(deleteImage);
            }
        }

        LinkedList<MoviesGalleriesEntity> moviesGalleriesEntities = moviesGalleriesService.findByMoviesByMovieFk(currentMoviesEntity);
        if (!currentMoviesEntity.getName().contains(moviesName)) {
            String pathToMovie = "/" + FileToDisk.NAME_ROOT_FOLDER + "/" + TypesFile.IMAGE.getNameFolder() + "/" + TypesGallery.MOVIES + "/";
            RenameFile.rename(pathToMovie + currentMoviesEntity.getName(), pathToMovie + moviesName);

            for (var oldPath : moviesGalleriesEntities) {
                oldPath.setPathToImage(pathToMovie + moviesName
                        + oldPath.getPathToImage().substring(oldPath.getPathToImage().lastIndexOf("/")));
                moviesGalleriesService.save(oldPath);
            }
            currentMoviesEntity.setPathToMainImage(pathToMovie + moviesName + currentMoviesEntity
                    .getPathToMainImage().substring(currentMoviesEntity.getPathToMainImage().lastIndexOf("/")));
        }

        if (!mainImage.isEmpty()) {
            DeleteFile.delete(currentMoviesEntity.getPathToMainImage());
            currentMoviesEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.MOVIES, moviesName, CreateMoviesRestController.NAME_MAIN_IMAGE, mainImage));
        }

        currentMoviesEntity.setName(moviesName);
        currentMoviesEntity.setDescription(moviesDescription);
        currentMoviesEntity.setTrailerLink(trailerLink);
        moviesService.save(currentMoviesEntity);

        if (galleryPictures != null) {
            LinkedList<String> pathToImages = new LinkedList<>();
            for (var picture : galleryPictures) {
                pathToImages.addLast(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.MOVIES, moviesName, UUID.randomUUID().toString(),
                        picture));
            }
            for (var pathToImage : pathToImages) {
                moviesGalleriesService.save(new MoviesGalleriesEntity(pathToImage, currentMoviesEntity));
            }
        }

        moviesTypeService.deleteByByMovieFk(currentMoviesEntity);
        for (var newType : newMoviesTypes) {
            moviesTypeService.save(new MoviesTypesEntity(newType, currentMoviesEntity));
        }

    }
}
