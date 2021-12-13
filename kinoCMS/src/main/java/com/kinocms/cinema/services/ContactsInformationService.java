package com.kinocms.cinema.services;

import com.kinocms.cinema.model.ContactsEntity;
import com.kinocms.cinema.model.ContactsInformationEntity;
import com.kinocms.cinema.repositories.ContactsInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactsInformationService {
    @Autowired
    private ContactsInformationRepository contactsInformationRepository;

    public ContactsInformationEntity save(ContactsInformationEntity contactsEntity) {
        return contactsInformationRepository.save(contactsEntity);
    }

    public ContactsInformationEntity findById(int id) {
        Optional<ContactsInformationEntity> contactsEntity = contactsInformationRepository.findById(id);
        if (contactsEntity.isPresent()) {
            return contactsEntity.get();
        } else {
            return null;
        }
    }

    public List<ContactsInformationEntity> findAll() {
        return contactsInformationRepository.findAll();
    }

    public void deleteById(int id) {
        contactsInformationRepository.deleteById(id);
    }

    public List<ContactsInformationEntity> findByContactsByContactFk(ContactsEntity contactsInformationEntity) {
        return contactsInformationRepository.findByContactsByContactFk(contactsInformationEntity);
    }
}
