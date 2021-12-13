package com.kinocms.cinema.services;

import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.model.AdditionalPagesGalleriesEntity;
import com.kinocms.cinema.repositories.AdditionalPagesGalleriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalPagesGalleriesService {
    @Autowired
    private AdditionalPagesGalleriesRepository additionalPagesGalleriesRepository;

    public List<AdditionalPagesGalleriesEntity> findByAdditionalPagesByAdditionalPageFk(AdditionalPagesEntity additionalPagesEntity) {
        return additionalPagesGalleriesRepository.findByAdditionalPagesByAdditionalPageFk(additionalPagesEntity);
    }


    public AdditionalPagesGalleriesEntity save(AdditionalPagesGalleriesEntity additionalPagesGalleriesEntity) {
        return additionalPagesGalleriesRepository.save(additionalPagesGalleriesEntity);
    }

    public AdditionalPagesGalleriesEntity findById(int id) {
        Optional<AdditionalPagesGalleriesEntity> additionalPagesGalleriesEntity = additionalPagesGalleriesRepository.findById(id);
        if (additionalPagesGalleriesEntity.isPresent()) {
            return additionalPagesGalleriesEntity.get();
        } else {
            return null;
        }
    }

    public void deleteById(int id) {
        additionalPagesGalleriesRepository.deleteById(id);
    }
}
