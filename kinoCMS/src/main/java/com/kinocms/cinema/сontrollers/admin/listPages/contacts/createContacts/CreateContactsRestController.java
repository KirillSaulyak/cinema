package com.kinocms.cinema.сontrollers.admin.listPages.contacts.createContacts;

import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.ContactsInformationEntity;
import com.kinocms.cinema.services.ContactsInformationService;
import com.kinocms.cinema.services.ContactsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class CreateContactsRestController {
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private ContactsInformationService contactsInformationService;

    @PostMapping("/create-default-entry-Контакты")
    public void createContactsInformation(@RequestParam(name = "contactsInformation") String contactsInformation,
                           @RequestParam(name = "logo") MultipartFile logo) {
        Gson gson = new Gson();
        FileToDisk fileToDisk = new FileToDisk();

        ContactsInformationEntity contactsInformationEntity = gson.fromJson(contactsInformation, ContactsInformationEntity.class);

        contactsInformationEntity.setPathToLogo(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CONTACTS, contactsInformationEntity.getNameCinema(),
                "mainImage", logo));
        contactsInformationEntity.setContactsByContactFk(contactsService.findAll().get(0));
        contactsInformationService.save(contactsInformationEntity);
    }
}
