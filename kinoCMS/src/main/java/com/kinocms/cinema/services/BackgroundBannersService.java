package com.kinocms.cinema.services;

import com.kinocms.cinema.model.BackgroundBannersEntity;
import com.kinocms.cinema.repositories.BackgroundBannersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BackgroundBannersService {
    @Autowired
    private BackgroundBannersRepository backgroundBannersRepository;

    public BackgroundBannersEntity findFirst() {
        if (backgroundBannersRepository.findFirst() != null) {
            return backgroundBannersRepository.findFirst();
        } else {
            return new BackgroundBannersEntity();
        }
    }

    public BackgroundBannersEntity save(BackgroundBannersEntity topBannersEntity) {
        return backgroundBannersRepository.save(topBannersEntity);
    }

    public BackgroundBannersEntity findById(int id) {
        Optional<BackgroundBannersEntity> backgroundBannerEntity = backgroundBannersRepository.findById(id);
        if (backgroundBannerEntity.isPresent()) {
            return backgroundBannerEntity.get();
        } else {
            return null;
        }
    }
}
