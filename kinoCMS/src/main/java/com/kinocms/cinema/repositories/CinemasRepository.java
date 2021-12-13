package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.CinemasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemasRepository extends JpaRepository<CinemasEntity,Integer> {
}
