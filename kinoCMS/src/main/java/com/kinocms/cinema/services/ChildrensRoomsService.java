package com.kinocms.cinema.services;

import com.kinocms.cinema.model.ChildrensRoomsEntity;
import com.kinocms.cinema.repositories.ChildrensRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildrensRoomsService {
    @Autowired
    private ChildrensRoomsRepository childrensRoomsRepository;

    public ChildrensRoomsEntity findFirst()
    {
        return childrensRoomsRepository.findFirst();
    }
    public ChildrensRoomsEntity save(ChildrensRoomsEntity childrensRoomsEntity) {
        return childrensRoomsRepository.save(childrensRoomsEntity);
    }

    public ChildrensRoomsEntity findById(int id) {
        Optional<ChildrensRoomsEntity> childrensRoomsEntity = childrensRoomsRepository.findById(id);
        if (childrensRoomsEntity.isPresent()) {
            return childrensRoomsEntity.get();
        } else {
            return null;
        }
    }

    public List<ChildrensRoomsEntity> findAll() {
        return childrensRoomsRepository.findAll();
    }

    public void deleteById(int id) {
        childrensRoomsRepository.deleteById(id);
    }
}
