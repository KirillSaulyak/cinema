package com.kinocms.cinema.сontrollers.kinocms.page.contactsPage;

import com.kinocms.cinema.model.ContactsEntity;
import com.kinocms.cinema.model.ContactsInformationEntity;
import com.kinocms.cinema.services.ContactsInformationService;
import com.kinocms.cinema.services.ContactsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserContactsPageRestController {
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private ContactsInformationService contactsInformationService;

    @PostMapping("/send-contact-information")
    public Map<String, String> sendContactInformation() {
        Gson gson = new Gson();
        Map<String, String> contactsInformation = new HashMap<>();

        ContactsEntity contactsEntity = contactsService.findFirst();
        contactsInformation.put("contact", gson.toJson(contactsEntity));

        List<ContactsInformationEntity> contactsList = contactsInformationService.findByContactsByContactFk(contactsEntity);
        contactsInformation.put("contactsList", gson.toJson(contactsList));

        return contactsInformation;
    }
}
