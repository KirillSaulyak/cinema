package com.kinocms.cinema.services;

import com.kinocms.cinema.model.DealsEntity;
import com.kinocms.cinema.model.DealsGalleriesEntity;
import com.kinocms.cinema.repositories.DealsGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealsGalleryService {
    @Autowired
    private DealsGalleryRepository dealsGalleryRepository;

    public List<DealsGalleriesEntity> findByDealsByDealFk(DealsEntity dealsEntity)
    {
        return dealsGalleryRepository.findByDealsByDealFk(dealsEntity);
    }


    public DealsGalleriesEntity save(DealsGalleriesEntity dealsGalleriesEntity)
    {
        return dealsGalleryRepository.save(dealsGalleriesEntity);
    }

    public DealsGalleriesEntity findById(int id) {
        Optional<DealsGalleriesEntity> dealsGalleriesEntity = dealsGalleryRepository.findById(id);
        if (dealsGalleriesEntity.isPresent()) {
            return dealsGalleriesEntity.get();
        } else {
            return null;
        }
    }

    public void deleteById(int id)
    {
        dealsGalleryRepository.deleteById(id);
    }
}
