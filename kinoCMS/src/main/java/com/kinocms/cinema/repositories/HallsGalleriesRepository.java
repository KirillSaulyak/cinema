package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.HallsEntity;
import com.kinocms.cinema.model.HallsGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallsGalleriesRepository extends JpaRepository<HallsGalleriesEntity,Integer> {
    List<HallsGalleriesEntity> getByHallsByHallFk(HallsEntity cinemasEntity);
}
