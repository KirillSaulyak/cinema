package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.HiddenMoviesEntity;
import com.kinocms.cinema.model.MoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiddenMoviesRepository extends JpaRepository<HiddenMoviesEntity, Integer> {
    void deleteByMoviesByMovieFk(MoviesEntity fk);
}
