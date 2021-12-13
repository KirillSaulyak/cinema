package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.AuthorizationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationsRepository extends JpaRepository<AuthorizationsEntity,Integer> {
}
