package com.kinocms.cinema.сontrollers.admin.listPages.vipHall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class VipHallController {
    @GetMapping("/edit-default-entry-Vip-зал")
    public String showMainPage() {
        return "admin/pages/VipHall";
    }
}
