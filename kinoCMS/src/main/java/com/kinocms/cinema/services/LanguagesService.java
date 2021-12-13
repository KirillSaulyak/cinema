package com.kinocms.cinema.services;

import com.kinocms.cinema.model.LanguagesEntity;
import com.kinocms.cinema.repositories.LanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguagesService {
    @Autowired
    private LanguagesRepository languagesRepository;

    public LanguagesEntity save(LanguagesEntity languagesEntity)
    {
        return languagesRepository.save(languagesEntity);
    }

    public LanguagesEntity findById(int id) {
        Optional<LanguagesEntity> languagesEntity = languagesRepository.findById(id);
        if (languagesEntity.isPresent()) {
            return languagesEntity.get();
        } else {
            return null;
        }
    }

    public List<LanguagesEntity> findAll() {
        return languagesRepository.findAll();
    }
}
