package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.VipHallsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VipHallsRepository extends JpaRepository<VipHallsEntity,Integer> {
    @Query(value = "SELECT * FROM vip_halls ORDER BY id_vip_hall LIMIT 1",nativeQuery = true)
    VipHallsEntity findFirst();
}
