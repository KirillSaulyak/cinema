package com.kinocms.cinema.сontrollers.admin.listPages.advertisement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdvertisementController {
    @GetMapping("/edit-default-entry-Реклама")
    public String showCafePage() {
        return "admin/pages/Advertisement";
    }
}
