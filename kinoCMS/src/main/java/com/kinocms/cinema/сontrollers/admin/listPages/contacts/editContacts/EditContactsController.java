package com.kinocms.cinema.сontrollers.admin.listPages.contacts.editContacts;

    import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class EditContactsController {
    @GetMapping("/edit-default-entry-Контакты")
    public String showMainPage() {
        return "admin/pages/contacts/EditContacts";
    }
}
