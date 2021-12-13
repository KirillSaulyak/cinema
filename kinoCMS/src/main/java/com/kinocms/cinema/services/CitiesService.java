package com.kinocms.cinema.services;

import com.kinocms.cinema.model.CitiesEntity;
import com.kinocms.cinema.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;

    public CitiesEntity save(CitiesEntity citiesEntity)
    {
        return citiesRepository.save(citiesEntity);
    }

    public CitiesEntity findById(int id) {
        Optional<CitiesEntity> citiesEntity = citiesRepository.findById(id);
        if (citiesEntity.isPresent()) {
            return citiesEntity.get();
        } else {
            return null;
        }
    }

    public List<CitiesEntity> findAll() {
        return citiesRepository.findAll();
    }
}
