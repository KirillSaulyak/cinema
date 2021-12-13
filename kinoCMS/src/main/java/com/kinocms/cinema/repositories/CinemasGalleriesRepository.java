package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.CinemasEntity;
import com.kinocms.cinema.model.CinemasGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemasGalleriesRepository extends JpaRepository<CinemasGalleriesEntity,Integer> {
    List<CinemasGalleriesEntity> getByCinemasByCinemaFk(CinemasEntity cinemasEntity);
}
