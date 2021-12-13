package com.kinocms.cinema.сontrollers.admin.deal.dealsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DealsPageController {
    @GetMapping("/deals")
    public String showDeals() {
        return "admin/deal/DealsPage";
    }
}
