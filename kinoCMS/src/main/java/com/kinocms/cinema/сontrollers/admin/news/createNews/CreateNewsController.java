package com.kinocms.cinema.сontrollers.admin.news.createNews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CreateNewsController {
    @GetMapping("/create-news")
    public String createNews()
    {
        return "admin/news/CreateNews";
    }
}
