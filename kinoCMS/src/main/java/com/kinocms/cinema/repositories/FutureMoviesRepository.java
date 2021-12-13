package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.FutureMoviesEntity;
import com.kinocms.cinema.model.MoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FutureMoviesRepository extends JpaRepository<FutureMoviesEntity, Integer> {
    void deleteByMoviesByMovieFk(MoviesEntity fk);
}
