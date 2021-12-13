package com.kinocms.cinema.сontrollers.kinocms.page.listCinemasPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserListCinemasPageController {
    @GetMapping("/cinemas")
    public String showCinemasPage()
    {
        return "kinocms/listCinemasPage/ListCinemasPage";
    }
}
