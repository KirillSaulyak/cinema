package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.DescriptionCurrentCinemasEntity;
import com.kinocms.cinema.model.DescriptionCurrentCinemasGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescriptionCurrentCinemasGalleriesRepository extends JpaRepository<DescriptionCurrentCinemasGalleriesEntity,Integer> {
    List<DescriptionCurrentCinemasGalleriesEntity> findByDescriptionCurrentCinemasByDescriptionCurrentCinemaFk(DescriptionCurrentCinemasEntity descriptionCurrentCinemasEntity);
}
