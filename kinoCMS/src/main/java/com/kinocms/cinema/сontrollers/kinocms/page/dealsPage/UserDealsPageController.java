package com.kinocms.cinema.сontrollers.kinocms.page.dealsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDealsPageController {
    @GetMapping("/deal")
    public String ShowDealPage()
    {
        return "kinocms/dealsPage/DealsPage";
    }
}
