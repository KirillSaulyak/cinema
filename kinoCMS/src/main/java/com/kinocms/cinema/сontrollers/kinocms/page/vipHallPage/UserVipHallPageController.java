package com.kinocms.cinema.сontrollers.kinocms.page.vipHallPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserVipHallPageController {
    @GetMapping("/vip-hall")
    public String showVipHallPage() {
        return "kinocms/vipHallPage/VipHallPage";
    }
}
