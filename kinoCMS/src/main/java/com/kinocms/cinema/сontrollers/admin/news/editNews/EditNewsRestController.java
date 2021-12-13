package com.kinocms.cinema.сontrollers.admin.news.editNews;

import com.kinocms.cinema.filesManipulation.*;
import com.kinocms.cinema.model.NewsEntity;
import com.kinocms.cinema.model.NewsGalleriesEntity;
import com.kinocms.cinema.services.NewsGalleriesService;
import com.kinocms.cinema.services.NewsService;
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
public class EditNewsRestController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsGalleriesService newsGalleriesService;

    @PostMapping("/edit-news")
    public void editNews(@RequestParam(name = "newsInformation") String newsInformation,
                         @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                         @RequestParam(name = "newsGallery", required = false) List<MultipartFile> newsGallery,
                         @RequestParam(name = "deleteOldNewsImagesFromGallery", required = false) List<Integer> deleteOldNewsImagesFromGallery) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();
        FileToDisk fileToDisk = new FileToDisk();

        if (deleteOldNewsImagesFromGallery != null) {
            for (var id : deleteOldNewsImagesFromGallery) {
                DeleteFile.delete(newsGalleriesService.findById(id).getPathToImage());
                newsGalleriesService.deleteById(id);
            }
        }

        NewsEntity newNewsEntity = gson.fromJson(newsInformation, NewsEntity.class);

        NewsEntity oldNewsEntity = newsService.findById(newNewsEntity.getIdNews());

        newNewsEntity.setCreationDate(oldNewsEntity.getCreationDate());
        if (mainImage != null) {
            String oldPathToMainImage = oldNewsEntity.getPathToMainImage();
            if (oldPathToMainImage != null) {
                DeleteFile.delete(oldPathToMainImage);
            }
            newNewsEntity.setPathToMainImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.NEWS, newNewsEntity.getName(),
                    "mainImage", mainImage));
        } else {
            newNewsEntity.setPathToMainImage(oldNewsEntity.getPathToMainImage());
        }

        if (oldNewsEntity != null && !oldNewsEntity.getName().contains(newNewsEntity.getName())) {
            List<NewsGalleriesEntity> oldNewsGalleriesEntities = newsGalleriesService.findByNewsByNewsFk(oldNewsEntity);
            String pathToNews = "/" + FileToDisk.NAME_ROOT_FOLDER + "/" + TypesFile.IMAGE.getNameFolder() + "/" + TypesGallery.NEWS + "/";
            RenameFile.rename(pathToNews + oldNewsEntity.getName(), pathToNews + newNewsEntity.getName());
            for (var oldPath : oldNewsGalleriesEntities) {
                oldPath.setPathToImage(pathToNews + newNewsEntity.getName()
                        + oldPath.getPathToImage().substring(oldPath.getPathToImage().lastIndexOf("/")));
                newsGalleriesService.save(oldPath);
            }
            newNewsEntity.setPathToMainImage(pathToNews + newNewsEntity.getName() + oldNewsEntity
                    .getPathToMainImage().substring(oldNewsEntity.getPathToMainImage().lastIndexOf("/")));
        }

        newsService.save(newNewsEntity);
        if (newsGallery != null) {
            for (var image : newsGallery) {
                newsGalleriesService.save(new NewsGalleriesEntity(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.NEWS,
                        newNewsEntity.getName(), UUID.randomUUID().toString(), image), newNewsEntity));
            }
        }
    }
}
