package com.kinocms.cinema.filesManipulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class RenameFile {
    private static Logger logger = LogManager.getLogger(RenameFile.class.getName());

    public static boolean rename(String oldPath, String newPath) {
        logger.info("renameFile");
        File oldFile = new File(FileToDisk.ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH + oldPath);
        if (oldFile.exists() && !oldPath.contains(newPath)) {
            return oldFile.renameTo(new File(FileToDisk.ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH + newPath));
        } else
            return false;
    }
}
