package com.kinocms.cinema.сontrollers.kinocms.page.hallsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHallsPageController {
    @GetMapping("/hall")
    public String showHallsPage()
    {
        return "kinocms/hallsPage/HallsPage";
    }
}
