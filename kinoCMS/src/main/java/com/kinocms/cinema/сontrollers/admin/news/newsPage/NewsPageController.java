package com.kinocms.cinema.сontrollers.admin.news.newsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class NewsPageController {
    @GetMapping("/news")
    public String showNews() {
        return "admin/news/NewsPage";
    }
}
