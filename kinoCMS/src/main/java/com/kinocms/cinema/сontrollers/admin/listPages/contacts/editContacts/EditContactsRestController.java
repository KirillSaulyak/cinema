package com.kinocms.cinema.сontrollers.admin.listPages.contacts.editContacts;

import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.ContactsEntity;
import com.kinocms.cinema.model.ContactsInformationEntity;
import com.kinocms.cinema.services.ContactsInformationService;
import com.kinocms.cinema.services.ContactsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class EditContactsRestController {
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private ContactsInformationService contactsInformationService;

    @PostMapping("/save-default-entry-Контакты")
    public void saveContact(@RequestParam(name = "enabled") String enabled) {
        Gson gson = new Gson();

        ContactsEntity contactsEntity = contactsService.findFirst();
        contactsEntity.setEnabled(gson.fromJson(enabled, boolean.class));

        contactsService.save(contactsEntity);
    }

    @PostMapping("/send-default-entry-Контакты")
    public String sendContact() {
        Gson gson = new Gson();

        ContactsEntity contactsEntity = contactsService.findFirst();

        return gson.toJson(contactsEntity.isEnabled());
    }

    @PostMapping("/edit-default-entry-contacts-information")
    public String sendContactsInformation() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();

        return  gson.toJson(contactsInformationService.findByContactsByContactFk(contactsService.findFirst()));
    }

    @PostMapping("/save-default-entry-contacts-information")
    public void saveContactsInformation(@RequestParam(name = "contactsInformation") String contactsInformation,
                                        @RequestParam(name = "logo", required = false) MultipartFile logo) {

        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        FileToDisk fileToDisk = new FileToDisk();

        ContactsInformationEntity contactsInformationEntity = gson.fromJson(contactsInformation, ContactsInformationEntity.class);

        if (logo != null) {
            contactsInformationEntity.setPathToLogo(fileToDisk.uploadFile(TypesFile.IMAGE, TypesGallery.CONTACTS,
                    contactsInformationEntity.getNameCinema(), "mainImage", logo));
        }

        contactsInformationService.save(contactsInformationEntity);
    }
}
