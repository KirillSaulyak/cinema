package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.CitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<CitiesEntity,Integer> {
}
