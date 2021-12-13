package com.kinocms.cinema.services;

import com.kinocms.cinema.model.AdvertisementsEntity;
import com.kinocms.cinema.repositories.AdvertisementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementsService {
    @Autowired
    private AdvertisementsRepository advertisementsRepository;

    public AdvertisementsEntity findFirst()
    {
       return advertisementsRepository.findFirst();
    }

    public AdvertisementsEntity save(AdvertisementsEntity advertisementsEntity)
    {
        return advertisementsRepository.save(advertisementsEntity);
    }

    public AdvertisementsEntity findById(int id) {
        Optional<AdvertisementsEntity> advertisementsEntity = advertisementsRepository.findById(id);
        if (advertisementsEntity.isPresent()) {
            return advertisementsEntity.get();
        } else {
            return null;
        }
    }

    public List<AdvertisementsEntity> findAll() {
        return advertisementsRepository.findAll();
    }

    public void deleteById(int id) {
        advertisementsRepository.deleteById(id);
    }
}
