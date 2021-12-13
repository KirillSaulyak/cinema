package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.DealsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealsRepository extends JpaRepository<DealsEntity,Integer> {
}
