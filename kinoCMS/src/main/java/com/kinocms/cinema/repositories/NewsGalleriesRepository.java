package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.NewsEntity;
import com.kinocms.cinema.model.NewsGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsGalleriesRepository extends JpaRepository<NewsGalleriesEntity, Integer> {
    List<NewsGalleriesEntity> findByNewsByNewsFk(NewsEntity newsEntity);
}
