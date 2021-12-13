package com.kinocms.cinema.сontrollers.kinocms.page.listNewsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserListNewsPageController {
    @GetMapping("/list-news")
    public String showListNewsPage()
    {
        return "kinocms/listNewsPage/ListNewsPage";
    }
}
