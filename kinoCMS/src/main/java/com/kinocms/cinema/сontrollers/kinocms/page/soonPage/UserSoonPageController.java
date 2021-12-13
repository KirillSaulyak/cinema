package com.kinocms.cinema.сontrollers.kinocms.page.soonPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserSoonPageController {
    @GetMapping("/soon")
    public String showSoonPage()
    {
        return "kinocms/soonPage/SoonPage";
    }
}
