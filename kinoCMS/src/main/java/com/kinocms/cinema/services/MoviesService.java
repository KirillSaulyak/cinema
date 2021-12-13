package com.kinocms.cinema.services;

import com.kinocms.cinema.model.MoviesEntity;
import com.kinocms.cinema.model.TopBannersEntity;
import com.kinocms.cinema.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {
    @Autowired
    private MoviesRepository moviesRepository;

    public MoviesEntity save(MoviesEntity moviesEntity) {
        return moviesRepository.save(moviesEntity);
    }

    public List<MoviesEntity> findAll() {
        return moviesRepository.findAll();
    }

    public MoviesEntity findById(int id) {
        Optional<MoviesEntity> moviesEntity = moviesRepository.findById(id);
        if (moviesEntity.isPresent()) {
            return moviesEntity.get();
        } else {
            return null;
        }
    }

    public void deleteById(int id) {
        moviesRepository.deleteById(id);
    }
}
