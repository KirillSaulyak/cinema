package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.AdvertisementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdvertisementsRepository extends JpaRepository<AdvertisementsEntity,Integer> {
    @Query(value = "SELECT * FROM advertisements ORDER BY id_advertisement LIMIT 1",nativeQuery = true)
    AdvertisementsEntity findFirst();
}
