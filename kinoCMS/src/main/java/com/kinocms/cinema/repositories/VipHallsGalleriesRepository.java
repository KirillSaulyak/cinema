package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.VipHallsEntity;
import com.kinocms.cinema.model.VipHallsGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VipHallsGalleriesRepository extends JpaRepository<VipHallsGalleriesEntity,Integer> {
    List<VipHallsGalleriesEntity> findByVipHallsByVipHallFk(VipHallsEntity vipHallsEntity);
}
