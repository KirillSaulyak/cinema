package com.kinocms.cinema.сontrollers.kinocms.page.cinemasPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCinemasPageController {
    @GetMapping("/cinema")
    public String showCinemaPage()
    {
        return "kinocms/cinemasPage/CinemasPage";
    }
}
