package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.GendersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GendersRepository extends JpaRepository<GendersEntity,Integer> {
}
