package com.kinocms.cinema.services;

import com.kinocms.cinema.model.MailingsEntity;
import com.kinocms.cinema.repositories.MailingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MailingsService {
    @Autowired
    private MailingRepository mailingRepository;

    public Integer getMinId() {
        return mailingRepository.getMinId();
    }

    public Integer getCountMailings() {
        return mailingRepository.getCountMailings();
    }

    public MailingsEntity save(MailingsEntity mailingsEntity) {
        return mailingRepository.save(mailingsEntity);
    }

    public MailingsEntity findById(int id) {
        Optional<MailingsEntity> mailingsEntity = mailingRepository.findById(id);
        if (mailingsEntity.isPresent()) {
            return mailingsEntity.get();
        } else {
            return null;
        }
    }

    public List<MailingsEntity> findAll() {
        return mailingRepository.findAll();
    }

    public void deleteById(int id) {
        mailingRepository.deleteById(id);
    }
}
