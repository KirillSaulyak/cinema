package com.kinocms.cinema.сontrollers.admin.listPages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ListPagesController {
    @GetMapping("/list-pages")
    public String showListPages() {
        return "admin/pages/ListPagesPage";
    }
}
