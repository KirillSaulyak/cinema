package com.kinocms.cinema.сontrollers.kinocms.page.advertisementPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAdvertisementPageController {
    @GetMapping("/advertisement")
    public String showAdvertisementPage()
    {
        return "kinocms/advertisementPage/AdvertisementPage";
    }
}
