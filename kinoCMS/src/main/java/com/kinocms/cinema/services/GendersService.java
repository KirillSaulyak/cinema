package com.kinocms.cinema.services;

import com.kinocms.cinema.model.GendersEntity;
import com.kinocms.cinema.repositories.GendersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GendersService {
    @Autowired
    private GendersRepository gendersRepository;

    public GendersEntity save(GendersEntity gendersEntity)
    {
        return gendersRepository.save(gendersEntity);
    }

    public GendersEntity findById(int id) {
        Optional<GendersEntity> gendersEntity = gendersRepository.findById(id);
        if (gendersEntity.isPresent()) {
            return gendersEntity.get();
        } else {
            return null;
        }
    }

    public List<GendersEntity> findAll() {
        return gendersRepository.findAll();
    }
}
