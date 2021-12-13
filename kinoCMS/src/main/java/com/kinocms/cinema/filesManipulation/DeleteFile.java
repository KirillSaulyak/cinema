package com.kinocms.cinema.filesManipulation;

import com.kinocms.cinema.сontrollers.kinocms.page.cafePage.UserCafePageController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class DeleteFile {
    private static Logger logger = LogManager.getLogger(UserCafePageController.class.getName());
    public static boolean delete(String path) {
        logger.info("delete file" + path);
        File deleteFile = new File(FileToDisk.ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH + path);
        if (deleteFile.exists()) {
            return deleteFile.delete();
        } else {
            return false;
        }
    }
}
