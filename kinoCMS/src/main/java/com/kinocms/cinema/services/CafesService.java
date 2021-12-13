package com.kinocms.cinema.services;

import com.kinocms.cinema.model.CafesEntity;

import com.kinocms.cinema.repositories.CafesRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CafesService {
    @Autowired
    private CafesRepositories cafesRepositories;

    public CafesEntity findFirst() {
        return cafesRepositories.findFirst();
    }

    public CafesEntity save(CafesEntity cafesEntity) {
        return cafesRepositories.save(cafesEntity);
    }

    public CafesEntity findById(int id) {
        Optional<CafesEntity> cafesEntity = cafesRepositories.findById(id);
        if (cafesEntity.isPresent()) {
            return cafesEntity.get();
        } else {
            return null;
        }
    }

    public List<CafesEntity> findAll() {
        return cafesRepositories.findAll();
    }

    public void deleteById(int id) {
        cafesRepositories.deleteById(id);
    }
}
