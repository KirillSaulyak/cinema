package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.AdvertisementsEntity;
import com.kinocms.cinema.model.AdvertisementsGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementsGalleriesRepository extends JpaRepository<AdvertisementsGalleriesEntity,Integer> {
    List<AdvertisementsGalleriesEntity> findByAdvertisementsByAdvertisementFk(AdvertisementsEntity advertisementsEntity);
}
