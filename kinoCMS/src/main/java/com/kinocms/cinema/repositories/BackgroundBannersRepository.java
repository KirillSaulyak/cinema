package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.BackgroundBannersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BackgroundBannersRepository extends JpaRepository<BackgroundBannersEntity,Integer> {
    @Query(value = "SELECT * FROM background_banners ORDER BY id_background_banner LIMIT 1",nativeQuery = true)
    BackgroundBannersEntity findFirst();
}
