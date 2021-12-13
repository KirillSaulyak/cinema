package com.kinocms.cinema.сontrollers.kinocms.page.cafePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCafePageController {
    private static Logger logger = LogManager.getLogger(UserCafePageController.class.getName());

    @GetMapping("/cafe")
    public String showCafePage() {
        logger.info("show cafe page");
        return "kinocms/cafesPage/CafesPage";
    }
}
