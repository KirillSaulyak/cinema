package com.kinocms.cinema.сontrollers.admin.listPages.mainPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainPageController {

    @GetMapping("/edit-default-entry-Главная страница")
    public String showMainPage() {
        return "admin/pages/MainPage";
    }
}
