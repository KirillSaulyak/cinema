package com.kinocms.cinema.services;

import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.repositories.CinemasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemasService {
    @Autowired
    private CinemasRepository cinemasRepository;

    public CinemasEntity findById(int id) {
        Optional<CinemasEntity> cinemasEntity = cinemasRepository.findById(id);
        if (cinemasEntity.isPresent()) {
            return cinemasEntity.get();
        } else {
            return null;
        }
    }

    public List<CinemasEntity> findAll() {
        return cinemasRepository.findAll();
    }

    public void deleteById(int id) {
        cinemasRepository.deleteById(id);
    }

    public CinemasEntity save(CinemasEntity cinemasEntity) {
        return cinemasRepository.save(cinemasEntity);
    }
}
