package com.kinocms.cinema.сontrollers.admin.movie.createMoviesPage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class CreateMoviesController {

    @GetMapping("/create-movie")
    public String createMoviesPage() {
        return "admin/movies/CreateMoviesPage";
    }

}