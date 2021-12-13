package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.AdditionalPagesEntity;
import com.kinocms.cinema.model.AdditionalPagesGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalPagesGalleriesRepository extends JpaRepository<AdditionalPagesGalleriesEntity,Integer> {
    List<AdditionalPagesGalleriesEntity> findByAdditionalPagesByAdditionalPageFk(AdditionalPagesEntity additionalPagesEntity);
}
