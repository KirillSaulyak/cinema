package com.kinocms.cinema.сontrollers.admin.deal.createDeals;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CreateDealController {
    @GetMapping("/create-deal")
    public String createDeal()
    {
        return "admin/deal/CreateDeal";
    }
}
