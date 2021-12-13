package com.kinocms.cinema.сontrollers.admin.mainPagesBanners;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class MainPagesBannersRestController {
    @Autowired
    private TopBannersService topBannersService;
    @Autowired
    private TopBannersGalleriesWithInformationService topBannersGalleriesWithInformationService;
    @Autowired
    private BackgroundBannersService backgroundBannersService;
    @Autowired
    private NewsAndDealsBannersService newsAndDealsBannersService;
    @Autowired
    private NewsAndDealsBannersGalleriesWithInformationService newsAndDealsBannersGalleriesWithInformationService;

    @PostMapping("/edit-top-banners")
    public void editTopBanners(@RequestParam(name = "newTopBanners", required = false) String newTopBanners,
                               @RequestParam(name = "oldTopBanners", required = false) String oldTopBanners,
                               @RequestParam(name = "deleteOldTopBannersById", required = false) LinkedList<Integer> deleteOldTopBannersById,
                               @RequestParam(name = "settingTopBanners") String settingTopBanners,
                               @RequestParam(name = "addToTopBannersGalleryPicture", required = false) MultipartFile[] addToGalleryPictures) {

        Gson jsonObject = new Gson();
        FileToDisk fileToDisk = new FileToDisk();
        TopBannersEntity topBannersEntity = jsonObject.fromJson(settingTopBanners, TopBannersEntity.class);
        TopBannersEntity oldTopBannersEntity = topBannersService.findFirst();
        if (oldTopBannersEntity != null) {
            topBannersEntity.setIdTopBanner(oldTopBannersEntity.getIdTopBanner());
        }

        topBannersEntity = topBannersService.save(topBannersEntity);


        if (deleteOldTopBannersById != null) {
            for (var bannersId : deleteOldTopBannersById) {
                DeleteFile.delete(topBannersGalleriesWithInformationService.findById(bannersId).getPathToImage());
                topBannersGalleriesWithInformationService.deleteById(bannersId);
            }
        }

        if (oldTopBanners != null) {
            String[] arrOldTopBanners = oldTopBanners.replaceAll("},\\{", "},;\\{").split(",;");
            for (int i = 0; i < arrOldTopBanners.length; i++) {
                TopBannersGalleriesWithInformationEntity topBannersGalleriesWithInformationEntity
                        = jsonObject.fromJson(arrOldTopBanners[i], TopBannersGalleriesWithInformationEntity.class);
                topBannersGalleriesWithInformationEntity.setTopBannersByTopBannersFk(topBannersEntity);
                topBannersGalleriesWithInformationService.save(topBannersGalleriesWithInformationEntity);
            }

        }
        if (newTopBanners != null) {
            String[] arrNewTopBanners = newTopBanners.replaceAll("},\\{", "},;\\{").split(",;");
            for (int i = 0; i < arrNewTopBanners.length; i++) {
                TopBannersGalleriesWithInformationEntity topBannersGalleriesWithInformationEntity
                        = jsonObject.fromJson(arrNewTopBanners[i], TopBannersGalleriesWithInformationEntity.class);
                topBannersGalleriesWithInformationEntity.setPathToImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.BANNERS, "Top banners",
                        UUID.randomUUID().toString(), addToGalleryPictures[i]));
                topBannersGalleriesWithInformationEntity.setTopBannersByTopBannersFk(topBannersService.save(topBannersEntity));
                topBannersGalleriesWithInformationService.save(topBannersGalleriesWithInformationEntity);
            }
        }
    }

    @PostMapping("/edit-background-banner")
    public void editBackgroundBanners(@RequestParam(name = "newBackgroundBannerSetting") String newBackgroundBannerSetting,
                                      @RequestParam(name = "newBackgroundBannersImage", required = false) MultipartFile backgroundBannersImage) {

        Gson jsonObject = new Gson();
        FileToDisk fileToDisk = new FileToDisk();
        BackgroundBannersEntity backgroundBannersEntity = backgroundBannersService.findFirst();
        if (backgroundBannersEntity.getIdBackgroundBanner() != 0) {
            backgroundBannersEntity.setEnabled(jsonObject.fromJson(newBackgroundBannerSetting, BackgroundBannersEntity.class).isEnabled());
        } else {
            backgroundBannersEntity = jsonObject.fromJson(newBackgroundBannerSetting, BackgroundBannersEntity.class);
        }

        if (backgroundBannersImage != null) {
            DeleteFile.delete(backgroundBannersEntity.getPathToBackgroundBanner());
            backgroundBannersEntity.setPathToBackgroundBanner(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.BANNERS,
                    "BackgroundBanner", UUID.randomUUID().toString(), backgroundBannersImage));
        }
        backgroundBannersService.save(backgroundBannersEntity);
    }

    @PostMapping("/edit-bottom-banners")
    public void editBottomBanners(@RequestParam(name = "newBottomBanners", required = false) String newBottomBanners,
                                  @RequestParam(name = "oldBottomBanners", required = false) String oldBottomBanners,
                                  @RequestParam(name = "deleteOldBottomBannersById", required = false) LinkedList<Integer> deleteOldBottomBannersById,
                                  @RequestParam(name = "settingBottomBanners") String settingBottomBanners,
                                  @RequestParam(name = "addToGalleryPicture", required = false) MultipartFile[] addToGalleryPictures) {

        Gson jsonObject = new Gson();
        FileToDisk fileToDisk = new FileToDisk();
        NewsAndDealsBannersEntity newsAndDealsBannersEntity = newsAndDealsBannersService.findFirst();

        if (newsAndDealsBannersEntity.getIdNewsAndDealsBanner() != 0) {
            newsAndDealsBannersEntity.setRotationSpeed(jsonObject.fromJson(settingBottomBanners, NewsAndDealsBannersEntity.class).getRotationSpeed());
            newsAndDealsBannersEntity.setEnabled(jsonObject.fromJson(settingBottomBanners, NewsAndDealsBannersEntity.class).isEnabled());
        } else {
            newsAndDealsBannersEntity = jsonObject.fromJson(settingBottomBanners, NewsAndDealsBannersEntity.class);
        }

        newsAndDealsBannersEntity = newsAndDealsBannersService.save(newsAndDealsBannersEntity);


        if (deleteOldBottomBannersById != null) {
            for (var bannersId : deleteOldBottomBannersById) {
                DeleteFile.delete(newsAndDealsBannersGalleriesWithInformationService.findById(bannersId).getPathToImage());
                newsAndDealsBannersGalleriesWithInformationService.deleteById(bannersId);
            }
        }

        if (oldBottomBanners != null) {
            String[] arrOldBottomBanners = oldBottomBanners.replaceAll("},\\{", "},;\\{").split(",;");
            for (int i = 0; i < arrOldBottomBanners.length; i++) {
                NewsAndDealsBannersGalleriesWithInformationEntity newsAndDealsBannersGalleriesWithInformationEntity
                        = jsonObject.fromJson(arrOldBottomBanners[i], NewsAndDealsBannersGalleriesWithInformationEntity.class);
                newsAndDealsBannersGalleriesWithInformationEntity.setNewsAndDealsBannersByNewsAndDealsBannersFk(newsAndDealsBannersEntity);
                newsAndDealsBannersGalleriesWithInformationService.save(newsAndDealsBannersGalleriesWithInformationEntity);
            }
        }

        if (newBottomBanners != null) {
            String[] arrNewBottomBanners = newBottomBanners.replaceAll("},\\{", "},;\\{").split(",;");
            for (int i = 0; i < arrNewBottomBanners.length; i++) {
                NewsAndDealsBannersGalleriesWithInformationEntity newsAndDealsBannersGalleriesWithInformationEntity
                        = jsonObject.fromJson(arrNewBottomBanners[i], NewsAndDealsBannersGalleriesWithInformationEntity.class);
                newsAndDealsBannersGalleriesWithInformationEntity.setPathToImage(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.BANNERS, "Bottom banners",
                        UUID.randomUUID().toString(), addToGalleryPictures[i]));
                newsAndDealsBannersGalleriesWithInformationEntity.setNewsAndDealsBannersByNewsAndDealsBannersFk(newsAndDealsBannersService.save(newsAndDealsBannersEntity));
                newsAndDealsBannersGalleriesWithInformationService.save(newsAndDealsBannersGalleriesWithInformationEntity);
            }
        }
    }
}
