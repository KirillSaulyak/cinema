package com.kinocms.cinema.services;

import com.kinocms.cinema.model.MainPagesInformationEntity;
import com.kinocms.cinema.repositories.MainPagesInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainPagesInformationService {
    @Autowired
    private MainPagesInformationRepository mainPagesInformationRepository;

    public MainPagesInformationEntity save(MainPagesInformationEntity mainPagesInformationEntity)
    {
        return mainPagesInformationRepository.save(mainPagesInformationEntity);
    }

    public MainPagesInformationEntity findById(int id) {
        Optional<MainPagesInformationEntity> mainPagesInformationEntity = mainPagesInformationRepository.findById(id);
        if (mainPagesInformationEntity.isPresent()) {
            return mainPagesInformationEntity.get();
        } else {
            return null;
        }
    }

    public List<MainPagesInformationEntity> findAll() {
        return mainPagesInformationRepository.findAll();
    }

    public void deleteById(int id) {
        mainPagesInformationRepository.deleteById(id);
    }
}
