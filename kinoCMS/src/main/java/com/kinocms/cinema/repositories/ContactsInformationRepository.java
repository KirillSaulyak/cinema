package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.ContactsEntity;
import com.kinocms.cinema.model.ContactsInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsInformationRepository extends JpaRepository<ContactsInformationEntity,Integer> {
    List<ContactsInformationEntity> findByContactsByContactFk(ContactsEntity contactsEntity);
}
