package com.kinocms.cinema.services;

import com.kinocms.cinema.model.DealsEntity;
import com.kinocms.cinema.repositories.DealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealsService {
    @Autowired
    private DealsRepository dealsRepository;

    public DealsEntity save(DealsEntity dealsEntity)
    {
        return dealsRepository.save(dealsEntity);
    }

    public DealsEntity findById(int id) {
        Optional<DealsEntity> dealsEntity = dealsRepository.findById(id);
        if (dealsEntity.isPresent()) {
            return dealsEntity.get();
        } else {
            return null;
        }
    }

    public List<DealsEntity> findAll() {
        return dealsRepository.findAll();
    }

    public void deleteById(int id) {
        dealsRepository.deleteById(id);
    }
}
