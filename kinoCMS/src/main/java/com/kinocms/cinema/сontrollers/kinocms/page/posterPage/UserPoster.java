package com.kinocms.cinema.сontrollers.kinocms.page.posterPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPoster {
    @GetMapping("/poster")
    public String showPosterPage()
    {
        return  "kinocms/posterPage/PosterPage";
    }
}
