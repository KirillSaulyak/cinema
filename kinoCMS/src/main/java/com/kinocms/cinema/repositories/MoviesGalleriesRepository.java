package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.MoviesEntity;
import com.kinocms.cinema.model.MoviesGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;

public interface MoviesGalleriesRepository extends JpaRepository<MoviesGalleriesEntity, Integer> {
    LinkedList<MoviesGalleriesEntity> findByMoviesByMovieFk(MoviesEntity movieFk);

    void deleteByPathToImage(String path);

}
