package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.DescriptionCurrentCinemasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DescriptionCurrentCinemasRepository extends JpaRepository<DescriptionCurrentCinemasEntity,Integer> {
    @Query(value = "SELECT * FROM description_current_cinemas ORDER BY id_description_current_cinema LIMIT 1",nativeQuery = true)
    DescriptionCurrentCinemasEntity findFirst();
}
