package com.kinocms.cinema.сontrollers.admin.mailing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MailingController {
    @GetMapping("/mailing")
    public String showMailingPage() {
        return "admin/mailing/Mailing";
    }

    @GetMapping("/mailing-user-list")
    public String showMailingUserList() {
        return "admin/mailing/UserSelectList";
    }
}
