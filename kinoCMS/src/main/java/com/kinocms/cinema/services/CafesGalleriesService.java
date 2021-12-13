package com.kinocms.cinema.services;

import com.kinocms.cinema.model.CafesEntity;
import com.kinocms.cinema.model.CafesGalleriesEntity;
import com.kinocms.cinema.repositories.CafesGalleriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CafesGalleriesService {
    @Autowired
    private CafesGalleriesRepository cafesGalleriesRepository;

    public List<CafesGalleriesEntity> findByCafesByCafeFk(CafesEntity cafesEntity)
    {
        return cafesGalleriesRepository.findByCafesByCafeFk(cafesEntity);
    }


    public CafesGalleriesEntity save(CafesGalleriesEntity cafesGalleriesEntity)
    {
        return cafesGalleriesRepository.save(cafesGalleriesEntity);
    }

    public CafesGalleriesEntity findById(int id) {
        Optional<CafesGalleriesEntity> cafesGalleriesEntity = cafesGalleriesRepository.findById(id);
        if (cafesGalleriesEntity.isPresent()) {
            return cafesGalleriesEntity.get();
        } else {
            return null;
        }
    }

    public void deleteById(int id)
    {
        cafesGalleriesRepository.deleteById(id);
    }
}
