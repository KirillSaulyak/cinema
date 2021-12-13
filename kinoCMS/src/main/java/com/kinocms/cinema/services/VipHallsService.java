package com.kinocms.cinema.services;

import com.kinocms.cinema.model.VipHallsEntity;
import com.kinocms.cinema.repositories.VipHallsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VipHallsService {
    @Autowired
    private VipHallsRepository vipHallsRepository;

    public VipHallsEntity findFirst()
    {
        return vipHallsRepository.findFirst();
    }

    public VipHallsEntity save(VipHallsEntity vipHallsEntity)
    {
        return vipHallsRepository.save(vipHallsEntity);
    }

    public VipHallsEntity findById(int id) {
        Optional<VipHallsEntity> vipHallesEntity = vipHallsRepository.findById(id);
        if (vipHallesEntity.isPresent()) {
            return vipHallesEntity.get();
        } else {
            return null;
        }
    }

    public List<VipHallsEntity> findAll() {
        return vipHallsRepository.findAll();
    }

    public void deleteById(int id) {
        vipHallsRepository.deleteById(id);
    }
}
