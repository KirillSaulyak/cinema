package com.kinocms.cinema.сontrollers.kinocms.page.schedulePage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserScheduleController {
    @GetMapping("/schedule")
    public String showSchedulePage()
    {
        return "kinocms/schedulePage/Schedule";
    }
}
