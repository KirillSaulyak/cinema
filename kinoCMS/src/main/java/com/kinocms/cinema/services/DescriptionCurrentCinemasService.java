package com.kinocms.cinema.services;

import com.kinocms.cinema.model.DescriptionCurrentCinemasEntity;
import com.kinocms.cinema.repositories.DescriptionCurrentCinemasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescriptionCurrentCinemasService {
    @Autowired
    private DescriptionCurrentCinemasRepository descriptionCurrentCinemasRepository;

    public DescriptionCurrentCinemasEntity findFirst()
    {
        return descriptionCurrentCinemasRepository.findFirst();
    }

    public DescriptionCurrentCinemasEntity save(DescriptionCurrentCinemasEntity descriptionCurrentCinemasEntity) {
        return descriptionCurrentCinemasRepository.save(descriptionCurrentCinemasEntity);
    }

    public DescriptionCurrentCinemasEntity findById(int id) {
        Optional<DescriptionCurrentCinemasEntity> descriptionCurrentCinemasEntity = descriptionCurrentCinemasRepository.findById(id);
        if (descriptionCurrentCinemasEntity.isPresent()) {
            return descriptionCurrentCinemasEntity.get();
        } else {
            return null;
        }
    }

    public List<DescriptionCurrentCinemasEntity> findAll() {
        return descriptionCurrentCinemasRepository.findAll();
    }

    public void deleteById(int id) {
        descriptionCurrentCinemasRepository.deleteById(id);
    }
}
