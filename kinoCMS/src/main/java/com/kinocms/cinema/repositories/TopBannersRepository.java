package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.TopBannersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopBannersRepository extends JpaRepository<TopBannersEntity,Integer > {
    @Query(value = "SELECT * FROM top_banners ORDER BY id_top_banner LIMIT 1",nativeQuery = true)
    TopBannersEntity findFirst();
}
