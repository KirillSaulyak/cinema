package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.DealsEntity;
import com.kinocms.cinema.model.DealsGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealsGalleryRepository extends JpaRepository<DealsGalleriesEntity,Integer> {
    List<DealsGalleriesEntity> findByDealsByDealFk(DealsEntity dealsEntity);
}
