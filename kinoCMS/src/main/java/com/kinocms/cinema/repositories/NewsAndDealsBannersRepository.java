package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.NewsAndDealsBannersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsAndDealsBannersRepository extends JpaRepository<NewsAndDealsBannersEntity,Integer> {
    @Query(value = "SELECT * FROM news_and_deals_banners ORDER BY id_news_and_deals_banner LIMIT 1",nativeQuery = true)
    NewsAndDealsBannersEntity findFirst();
}
