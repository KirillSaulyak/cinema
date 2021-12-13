package com.kinocms.cinema.services;

import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.model.CinemasGalleriesEntity;
import com.kinocms.cinema.model.HallsEntity;
import com.kinocms.cinema.model.HallsGalleriesEntity;
import com.kinocms.cinema.repositories.CinemasGalleriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemasGalleriesService {
    @Autowired
    private CinemasGalleriesRepository cinemasGalleriesRepository;

    public CinemasGalleriesEntity findById(int id) {
        Optional<CinemasGalleriesEntity> cinemasGalleriesEntity = cinemasGalleriesRepository.findById(id);
        if (cinemasGalleriesEntity.isPresent()) {
            return cinemasGalleriesEntity.get();
        } else {
            return null;
        }
    }

    public List<CinemasGalleriesEntity> getByCinemasByCinemaFk(CinemasEntity cinemasEntity)
    {
        return cinemasGalleriesRepository.getByCinemasByCinemaFk(cinemasEntity);
    }

    public CinemasGalleriesEntity save(CinemasGalleriesEntity cinemasGalleriesEntity) {
        return cinemasGalleriesRepository.save(cinemasGalleriesEntity);
    }

    public void deleteById(int id)
    {
        cinemasGalleriesRepository.deleteById(id);
    }

}
