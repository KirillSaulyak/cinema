package com.kinocms.cinema.services;

import com.kinocms.cinema.model.HallsEntity;
import com.kinocms.cinema.model.HallsGalleriesEntity;
import com.kinocms.cinema.repositories.HallsGalleriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallsGalleriesService {
    @Autowired
    private HallsGalleriesRepository hallsGalleriesRepository;

    public HallsGalleriesEntity findById(int id) {
        Optional<HallsGalleriesEntity> hallsGalleriesEntity = hallsGalleriesRepository.findById(id);
        if (hallsGalleriesEntity.isPresent()) {
            return hallsGalleriesEntity.get();
        } else {
            return null;
        }
    }

    public List<HallsGalleriesEntity> getByHallsByHallFk(HallsEntity hallsEntity)
    {
        return hallsGalleriesRepository.getByHallsByHallFk(hallsEntity);
    }

    public HallsGalleriesEntity save(HallsGalleriesEntity hallsGalleriesEntity) {
        return hallsGalleriesRepository.save(hallsGalleriesEntity);
    }

    public void deleteById(int id)
    {
        hallsGalleriesRepository.deleteById(id);
    }
}
