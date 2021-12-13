package com.kinocms.cinema.сontrollers.admin.listPages.additionalPage.create;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CreateAdditionalPageController {
    @GetMapping("/create-additional-page")
    public String createDeal()
    {
        return "admin/pages/additionalPage/CreateAdditionalPage";
    }
}
