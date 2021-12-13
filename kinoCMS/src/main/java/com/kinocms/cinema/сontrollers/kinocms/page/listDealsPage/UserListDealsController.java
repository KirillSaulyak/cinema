package com.kinocms.cinema.сontrollers.kinocms.page.listDealsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserListDealsController {
    @GetMapping("/deals")
    public String showListDealsPage()
    {
        return "kinocms/listDealsPage/ListDealsPage";
    }
}
