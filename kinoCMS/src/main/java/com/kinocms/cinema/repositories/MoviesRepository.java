package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.MoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<MoviesEntity,Integer> {
    List<MoviesEntity> getByName(String name);
}
