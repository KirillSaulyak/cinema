package com.kinocms.cinema.сontrollers.kinocms.page.mainPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMainPageController {

    @GetMapping("/kinocms")
    public String showMainPage()
    {
        return "kinocms/mainPage/MainPage";
    }
}
