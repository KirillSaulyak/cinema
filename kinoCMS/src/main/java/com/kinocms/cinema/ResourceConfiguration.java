package com.kinocms.cinema;

import com.kinocms.cinema.filesManipulation.FileToDisk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {
    private static Logger logger = LogManager.getLogger(ResourceConfiguration.class.getName());
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("setting resource");
        registry.addResourceHandler("/" + FileToDisk.NAME_ROOT_FOLDER + "/**")
                .addResourceLocations("file:///" + FileToDisk.ABSOLUTE_PICTURE_SOURCE_FOLDER_PATH + FileToDisk.NAME_ROOT_FOLDER + "/");
    }
}
