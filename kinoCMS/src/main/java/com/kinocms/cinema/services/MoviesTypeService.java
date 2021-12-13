package com.kinocms.cinema.services;

import com.kinocms.cinema.model.MoviesEntity;
import com.kinocms.cinema.model.MoviesTypesEntity;
import com.kinocms.cinema.repositories.MoviesTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;

@Service
public class MoviesTypeService {
    @Autowired
    private MoviesTypeRepository moviesTypeRepository;

    public void save(MoviesTypesEntity moviesTypesEntity) {
        moviesTypeRepository.save(moviesTypesEntity);
    }

    public LinkedList<MoviesTypesEntity> getByType(String name) {
        return moviesTypeRepository.getByType(name);
    }

    public LinkedList<MoviesTypesEntity> findByMoviesByMovieFk(MoviesEntity movieFk) {
        return moviesTypeRepository.findByMoviesByMovieFk(movieFk);
    }
    @Transactional
    public void deleteByByMovieFk(MoviesEntity movieFk)
    {
        moviesTypeRepository.deleteByMoviesByMovieFk(movieFk);
    }
}
