package com.kinocms.cinema.filesManipulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileToDisk {
    public static final String NAME_ROOT_FOLDER = "cmsFiles";
    public static final String ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH = System.getProperty("user.dir")
            .substring(0, System.getProperty("user.dir").lastIndexOf(File.separatorChar)) + File.separatorChar;
    private String pathTypeFile;
    private String pathTypeGallery;
    private String pathNameGallery;
    private String relativePathToImage;
    private static Logger logger = LogManager.getLogger(FileToDisk.class.getName());

    public String uploadFile(TypesFile typesFile, TypesGallery typeGallery, String nameGallery, String imagesName,
                             MultipartFile image) {
        try {
            logger.info("upload file");
            relativePathToImage = "/" + NAME_ROOT_FOLDER + "/" + typesFile.getNameFolder() + "/" + typeGallery.name()
                    + "/" + nameGallery + "/" + imagesName + "."
                    + image.getContentType().substring(image.getContentType().indexOf("/") + 1);

            pathTypeFile = ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH + NAME_ROOT_FOLDER + "/" + typesFile.getNameFolder();

            pathTypeGallery = pathTypeFile + "/" + typeGallery.name();

            pathNameGallery = pathTypeGallery + "/" + nameGallery + "/";

            createDirectory();

            image.transferTo(new File(ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH + relativePathToImage));
            return relativePathToImage;

        } catch (IOException exception) {
            logger.error(exception.getMessage());
            exception.fillInStackTrace();
        }

        return null;
    }

    private void createDirectory() {
        logger.info("create directory");
        File rootFolder = new File(ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH + NAME_ROOT_FOLDER);
        if (!rootFolder.exists()) {
            if(!rootFolder.mkdir())
            {
                logger.error("can`t made directory: " + rootFolder);
            }
        }

        File typeFileFolder = new File(pathTypeFile);
        if (!typeFileFolder.exists()) {
            if(!typeFileFolder.mkdir())
            {
                logger.error("can`t made directory: " + typeFileFolder);
            }
        }

        if (pathTypeGallery != null) {
            File typeGalleryFolder = new File(pathTypeGallery);
            if (!typeGalleryFolder.exists()) {
                if(!typeGalleryFolder.mkdir())
                {
                    logger.error("can`t made directory: " + typeGalleryFolder);
                }
            }
        }

        if (pathNameGallery != null) {
            File nameGalleryFolder = new File(pathNameGallery);
            if (!nameGalleryFolder.exists()) {
                if(!nameGalleryFolder.mkdir())
                {
                    logger.error("can`t made directory: " + nameGalleryFolder);
                }
            }
        }
    }
}
