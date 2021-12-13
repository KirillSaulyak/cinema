package com.kinocms.cinema.сontrollers.admin.user.commonUsersOperations;

import com.kinocms.cinema.model.AuthorizationsEntity;
import com.kinocms.cinema.model.UsersEntity;
import com.kinocms.cinema.services.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/admin")
public class commonUsersOperationsRestController {
    @Autowired
    private LanguagesService languagesService;
    @Autowired
    private GendersService gendersService;
    @Autowired
    private CitiesService citiesService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthorizationsService authorizationsService;

    @PostMapping("/user-save")
    public void saveUser(@RequestParam("userInformation") String userInformation,
                         @RequestParam("idChosenLanguage") Integer idChosenLanguage,
                         @RequestParam("idChosenGender") Integer idChosenGender,
                         @RequestParam("idChosenCity") Integer idChosenCity,
                         @RequestParam("userAuthorization") String userAuthorization) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd").create();

        UsersEntity usersEntity = gson.fromJson(userInformation, UsersEntity.class);
        usersEntity.setRegistrationDate(new Date(new java.util.Date().getTime()));
        usersEntity.setLanguagesByLanguageFk(languagesService.findById(idChosenLanguage));
        usersEntity.setGendersByGenderFk(gendersService.findById(idChosenGender));
        usersEntity.setCitiesByCityFk(citiesService.findById(idChosenCity));

        AuthorizationsEntity authorizationsEntity = gson.fromJson(userAuthorization, AuthorizationsEntity.class);
        usersEntity.setAuthorizationsByAuthorizationFk((authorizationsService.save(authorizationsEntity)));
        usersService.save(usersEntity);
    }
}
