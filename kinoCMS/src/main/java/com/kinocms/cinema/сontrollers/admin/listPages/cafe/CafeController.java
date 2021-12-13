package com.kinocms.cinema.сontrollers.admin.listPages.cafe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CafeController {
    @GetMapping("/edit-default-entry-Кафе-Бар")
    public String showCafePage() {
        return "admin/pages/Cafe";
    }
}
