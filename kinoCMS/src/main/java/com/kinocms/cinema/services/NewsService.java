package com.kinocms.cinema.services;

import com.kinocms.cinema.model.NewsEntity;
import com.kinocms.cinema.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public NewsEntity save(NewsEntity newsEntity)
    {
        return newsRepository.save(newsEntity);
    }

    public NewsEntity findById(int id) {
        Optional<NewsEntity> newsEntity = newsRepository.findById(id);
        if (newsEntity.isPresent()) {
            return newsEntity.get();
        } else {
            return null;
        }
    }

    public List<NewsEntity> findAll() {
        return newsRepository.findAll();
    }

    public void deleteById(int id) {
        newsRepository.deleteById(id);
    }
}
