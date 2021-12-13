package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.CafesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CafesRepositories extends JpaRepository<CafesEntity,Integer> {
    @Query(value = "SELECT * FROM cafes ORDER BY id_cafe LIMIT 1",nativeQuery = true)
    CafesEntity findFirst();
}
