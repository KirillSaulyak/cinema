package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.MoviesEntity;
import com.kinocms.cinema.model.MoviesTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface MoviesTypeRepository extends JpaRepository<MoviesTypesEntity,Integer> {
    LinkedList<MoviesTypesEntity> findByMoviesByMovieFk(MoviesEntity movieFk);
    LinkedList<MoviesTypesEntity> getByType(String name);

    void deleteByMoviesByMovieFk(MoviesEntity movieFk);
}
