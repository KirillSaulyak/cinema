package com.kinocms.cinema.сontrollers.admin.user.usersList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UsersListController {
    @GetMapping("/list-users")
    public String showListPages() {
        return "admin/users/UsersList";
    }
}
