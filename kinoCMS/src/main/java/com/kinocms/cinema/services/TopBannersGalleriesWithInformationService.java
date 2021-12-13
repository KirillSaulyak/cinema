package com.kinocms.cinema.services;

import com.kinocms.cinema.model.TopBannersGalleriesWithInformationEntity;
import com.kinocms.cinema.repositories.TopBannersGalleriesWithInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopBannersGalleriesWithInformationService {
    @Autowired
    private TopBannersGalleriesWithInformationRepository topBannersGalleriesWithInformationRepository;

    public TopBannersGalleriesWithInformationEntity save(TopBannersGalleriesWithInformationEntity topBannersGalleriesEntity) {
        return topBannersGalleriesWithInformationRepository.save(topBannersGalleriesEntity);
    }

    public TopBannersGalleriesWithInformationEntity findById(int id)
    {
        Optional<TopBannersGalleriesWithInformationEntity> topBannersGalleriesWithInformationEntity
                = topBannersGalleriesWithInformationRepository.findById(id);
        if (topBannersGalleriesWithInformationEntity.isPresent()) {
            return topBannersGalleriesWithInformationEntity.get();
        } else {
            return null;
        }
    }
    public List<TopBannersGalleriesWithInformationEntity> findAll() {
        return topBannersGalleriesWithInformationRepository.findAll();
    }


    public void deleteById(int id)
    {
        topBannersGalleriesWithInformationRepository.deleteById(id);
    }
}
