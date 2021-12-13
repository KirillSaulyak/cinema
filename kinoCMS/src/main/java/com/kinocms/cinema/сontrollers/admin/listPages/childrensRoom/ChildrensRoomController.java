package com.kinocms.cinema.сontrollers.admin.listPages.childrensRoom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ChildrensRoomController {
    @GetMapping("/edit-default-entry-Детская комната")
    public String showMainPage() {
        return "admin/pages/ChildrensRoom";
    }
}
