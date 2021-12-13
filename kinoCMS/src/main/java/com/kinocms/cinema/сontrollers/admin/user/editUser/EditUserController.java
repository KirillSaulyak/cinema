package com.kinocms.cinema.сontrollers.admin.user.editUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class EditUserController {

    @GetMapping("/edit-user")
    public String showUserEditPage()
    {
        return"admin/users/EditUser";
    }
}
