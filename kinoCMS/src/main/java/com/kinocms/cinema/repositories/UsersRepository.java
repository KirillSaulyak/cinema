package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {
}
