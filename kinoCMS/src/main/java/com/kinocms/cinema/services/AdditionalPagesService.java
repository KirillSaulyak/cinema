package com.kinocms.cinema.services;

import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.repositories.AdditionalPagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalPagesService {
    @Autowired
    private AdditionalPagesRepository additionalPagesRepository;

    public AdditionalPagesEntity save(AdditionalPagesEntity additionalPagesEntity)
    {
        return additionalPagesRepository.save(additionalPagesEntity);
    }

    public AdditionalPagesEntity findById(int id) {
        Optional<AdditionalPagesEntity> additionalPagesEntity = additionalPagesRepository.findById(id);
        if (additionalPagesEntity.isPresent()) {
            return additionalPagesEntity.get();
        } else {
            return null;
        }
    }

    public List<AdditionalPagesEntity> findAll() {
        return additionalPagesRepository.findAll();
    }

    public void deleteById(int id) {
        additionalPagesRepository.deleteById(id);
    }
}
