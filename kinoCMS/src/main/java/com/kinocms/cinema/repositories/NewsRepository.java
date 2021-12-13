package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity,Integer> {
}
