package com.kinocms.cinema.сontrollers.kinocms.page.aboutCurrentCinemaPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAboutCurrentCinemaPageController {
    @GetMapping("/about-cinema")
    public String showCurrentCinemaPage()
    {
        return "kinocms/aboutCurrentCinemaPage/AboutCurrentCinemaPage";
    }
}
