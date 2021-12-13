package com.kinocms.cinema.сontrollers.kinocms.page.moviesPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoviesPageController {
    @GetMapping("/movie")
    public String showMoviesPage()
    {
        return "kinocms/moviesPage/MoviesPage";
    }
}
