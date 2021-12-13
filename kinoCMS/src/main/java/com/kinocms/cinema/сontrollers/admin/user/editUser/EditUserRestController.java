package com.kinocms.cinema.сontrollers.admin.user.editUser;

import com.kinocms.cinema.services.CitiesService;
import com.kinocms.cinema.services.GendersService;
import com.kinocms.cinema.services.LanguagesService;
import com.kinocms.cinema.services.UsersService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class EditUserRestController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private LanguagesService languagesService;
    @Autowired
    private GendersService gendersService;
    @Autowired
    private CitiesService citiesService;

    @PostMapping("/get-user-information")
    public Map<String, String> sendUserInformation(@RequestParam("idUser") Integer idUser) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Map<String, String> defaultAndUserInformations = new HashMap<>();
        defaultAndUserInformations.put("usersInformation", gson.toJson(usersService.findById(idUser)));
        defaultAndUserInformations.put("defaultUsersLanguages", gson.toJson(languagesService.findAll()));
        defaultAndUserInformations.put("defaultUsersGenders", gson.toJson(gendersService.findAll()));
        defaultAndUserInformations.put("defaultUsersCities", gson.toJson(citiesService.findAll()));
        return defaultAndUserInformations;
    }
}
