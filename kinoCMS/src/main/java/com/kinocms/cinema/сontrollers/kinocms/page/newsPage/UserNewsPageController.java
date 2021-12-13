package com.kinocms.cinema.сontrollers.kinocms.page.newsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserNewsPageController {
    @GetMapping("/news")
    public String showNewsPage()
    {
        return "kinocms/newsPage/NewsPage";
    }
}
