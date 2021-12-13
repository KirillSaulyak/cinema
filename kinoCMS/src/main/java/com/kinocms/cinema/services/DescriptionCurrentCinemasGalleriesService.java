package com.kinocms.cinema.services;

import com.kinocms.cinema.model.DescriptionCurrentCinemasEntity;
import com.kinocms.cinema.model.DescriptionCurrentCinemasGalleriesEntity;
import com.kinocms.cinema.repositories.DescriptionCurrentCinemasGalleriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescriptionCurrentCinemasGalleriesService {
    @Autowired
    private DescriptionCurrentCinemasGalleriesRepository cinemasGalleriesRepository;

    public List<DescriptionCurrentCinemasGalleriesEntity> findByDescriptionCurrentCinemasByDescriptionCurrentCinemaFk(DescriptionCurrentCinemasEntity descriptionCurrentCinemasEntity)
    {
        return cinemasGalleriesRepository.findByDescriptionCurrentCinemasByDescriptionCurrentCinemaFk(descriptionCurrentCinemasEntity);
    }


    public DescriptionCurrentCinemasGalleriesEntity save(DescriptionCurrentCinemasGalleriesEntity descriptionCurrentCinemasGalleriesEntity)
    {
        return cinemasGalleriesRepository.save(descriptionCurrentCinemasGalleriesEntity);
    }

    public DescriptionCurrentCinemasGalleriesEntity findById(int id) {
        Optional<DescriptionCurrentCinemasGalleriesEntity> descriptionCurrentCinemasGalleriesEntity = cinemasGalleriesRepository.findById(id);
        if (descriptionCurrentCinemasGalleriesEntity.isPresent()) {
            return descriptionCurrentCinemasGalleriesEntity.get();
        } else {
            return null;
        }
    }

    public void deleteById(int id)
    {
        cinemasGalleriesRepository.deleteById(id);
    }
}
