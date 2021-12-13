package com.kinocms.cinema.сontrollers.kinocms.page.contactsPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserContactsPageController {
    @GetMapping("/contacts")
    public String showContactsPage()
    {
        return "kinocms/contactsPage/ContactsPage";
    }
}
