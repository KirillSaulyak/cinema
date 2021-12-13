package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.model.HallsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallsRepository extends JpaRepository<HallsEntity, Integer> {
    List<HallsEntity> findByCinemasByCinemaFk(CinemasEntity cinemasEntity);
}
