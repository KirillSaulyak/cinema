package com.kinocms.cinema.сontrollers.kinocms.page.additionalPagesPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAdditionalPagesPageController {
    @GetMapping("/additional-page")
    public String showAdditionalPage()
    {
        return "kinocms/additionalPagesPage/AdditionalPagesPage";
    }
}
