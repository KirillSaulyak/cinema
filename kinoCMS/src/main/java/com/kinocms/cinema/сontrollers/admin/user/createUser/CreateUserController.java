package com.kinocms.cinema.сontrollers.admin.user.createUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CreateUserController {
    @GetMapping("/create-user")
    public String createDeal()
    {
        return "admin/users/CreateUser";
    }
}
