package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.CafesEntity;
import com.kinocms.cinema.model.CafesGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafesGalleriesRepository extends JpaRepository<CafesGalleriesEntity,Integer> {
    List<CafesGalleriesEntity> findByCafesByCafeFk(CafesEntity cafesEntity);
}
