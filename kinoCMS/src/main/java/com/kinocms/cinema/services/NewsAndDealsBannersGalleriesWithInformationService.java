package com.kinocms.cinema.services;

import com.kinocms.cinema.model.NewsAndDealsBannersGalleriesWithInformationEntity;
import com.kinocms.cinema.repositories.NewsAndDealsBannersGalleriesWithInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsAndDealsBannersGalleriesWithInformationService {
    @Autowired
    private NewsAndDealsBannersGalleriesWithInformationRepository newsAndDealsBannersGalleriesWithInformationRepository;

    public NewsAndDealsBannersGalleriesWithInformationEntity save(NewsAndDealsBannersGalleriesWithInformationEntity newsAndDealsBannersGalleriesWithInformationEntity) {
        return newsAndDealsBannersGalleriesWithInformationRepository.save(newsAndDealsBannersGalleriesWithInformationEntity);
    }

    public NewsAndDealsBannersGalleriesWithInformationEntity findById(int id)
    {
        Optional<NewsAndDealsBannersGalleriesWithInformationEntity> newsAndDealsBannersGalleriesWithInformationEntity
                = newsAndDealsBannersGalleriesWithInformationRepository.findById(id);
        if (newsAndDealsBannersGalleriesWithInformationEntity.isPresent()) {
            return newsAndDealsBannersGalleriesWithInformationEntity.get();
        } else {
            return null;
        }
    }
    public List<NewsAndDealsBannersGalleriesWithInformationEntity> findAll() {
        return newsAndDealsBannersGalleriesWithInformationRepository.findAll();
    }


    public void deleteById(int id)
    {
        newsAndDealsBannersGalleriesWithInformationRepository.deleteById(id);
    }
}
