package com.kinocms.cinema.сontrollers.admin.cinema.createCinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CreateCinemaController {
    public static final String LINK_EDIT_CINEMA = "/create-cinema";
    @GetMapping(LINK_EDIT_CINEMA)
    public String editCinema(){
        return ("admin/cinemas/EditCinema");
    }
}
