package com.kinocms.cinema.сontrollers.admin.deal.dealsPage;

import com.kinocms.cinema.services.DealsService;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class DealsPageRestController {
    @Autowired
    private DealsService dealsService;

    @PostMapping("/deals")
    public String sendAllDeals()
    {
        return new GsonBuilder().setDateFormat("dd.MM.yyyy").create().toJson(dealsService.findAll());
    }
}
