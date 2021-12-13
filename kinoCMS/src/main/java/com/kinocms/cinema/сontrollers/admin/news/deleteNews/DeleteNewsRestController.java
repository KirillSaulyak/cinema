package com.kinocms.cinema.сontrollers.admin.news.deleteNews;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.NewsEntity;
import com.kinocms.cinema.model.NewsGalleriesEntity;
import com.kinocms.cinema.services.NewsGalleriesService;
import com.kinocms.cinema.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class DeleteNewsRestController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsGalleriesService newsGalleriesService;

    @DeleteMapping("/delete-news")
    public void deleteNews(@RequestParam("idNews") Integer idNews) {

        NewsEntity newsEntity = newsService.findById(idNews);
        List<NewsGalleriesEntity> newsGalleriesEntities =  newsGalleriesService.findByNewsByNewsFk(newsEntity);
        for (var picture : newsGalleriesEntities) {
            DeleteFile.delete(picture.getPathToImage());
        }

        DeleteFile.delete(newsEntity.getPathToMainImage());

        DeleteFile.delete(FileToDisk.NAME_ROOT_FOLDER + "/" +TypesFile.IMAGE.getNameFolder() + "/"
                + TypesGallery.NEWS + "/" + newsEntity.getName());

        newsService.deleteById(idNews);
    }
}
