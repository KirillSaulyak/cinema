package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.CurrentMoviesEntity;
import com.kinocms.cinema.model.MoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentMoviesRepository extends JpaRepository<CurrentMoviesEntity, Integer> {
    void deleteByMoviesByMovieFk(MoviesEntity fk);
}
