package com.kinocms.cinema.services;

import com.kinocms.cinema.model.NewsAndDealsBannersEntity;
import com.kinocms.cinema.repositories.NewsAndDealsBannersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsAndDealsBannersService {
    @Autowired
    private NewsAndDealsBannersRepository newsAndDealsBannersRepository;

    public NewsAndDealsBannersEntity findFirst() {
        if (newsAndDealsBannersRepository.findFirst() != null) {
            return newsAndDealsBannersRepository.findFirst();
        } else {
            return new NewsAndDealsBannersEntity();
        }
    }

    public NewsAndDealsBannersEntity save(NewsAndDealsBannersEntity newsAndDealsBannersEntity) {
        return newsAndDealsBannersRepository.save(newsAndDealsBannersEntity);
    }

    public List<NewsAndDealsBannersEntity> findAll() {
        return newsAndDealsBannersRepository.findAll();
    }

    public NewsAndDealsBannersEntity findById(int id) {
        Optional<NewsAndDealsBannersEntity> newsAndDealsBannersEntity = newsAndDealsBannersRepository.findById(id);
        if (newsAndDealsBannersEntity.isPresent()) {
            return newsAndDealsBannersEntity.get();
        } else {
            return null;
        }
    }
}
