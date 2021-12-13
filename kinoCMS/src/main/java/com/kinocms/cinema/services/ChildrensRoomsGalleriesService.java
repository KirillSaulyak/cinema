package com.kinocms.cinema.services;

import com.kinocms.cinema.model.ChildrensRoomsEntity;
import com.kinocms.cinema.model.ChildrensRoomsGalleriesEntity;
import com.kinocms.cinema.repositories.ChildrensRoomsGalleriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildrensRoomsGalleriesService {
    @Autowired
    private ChildrensRoomsGalleriesRepository childrensRoomsGalleriesRepository;

    public List<ChildrensRoomsGalleriesEntity> findByChildrensRoomsByChildrensRoomFk(ChildrensRoomsEntity childrensRoomsEntity)
    {
        return childrensRoomsGalleriesRepository.findByChildrensRoomsByChildrensRoomFk(childrensRoomsEntity);
    }


    public ChildrensRoomsGalleriesEntity save(ChildrensRoomsGalleriesEntity childrensRoomsGalleriesEntity)
    {
        return childrensRoomsGalleriesRepository.save(childrensRoomsGalleriesEntity);
    }

    public ChildrensRoomsGalleriesEntity findById(int id) {
        Optional<ChildrensRoomsGalleriesEntity> childrensRoomsGalleriesEntity = childrensRoomsGalleriesRepository.findById(id);
        if (childrensRoomsGalleriesEntity.isPresent()) {
            return childrensRoomsGalleriesEntity.get();
        } else {
            return null;
        }
    }

    public void deleteById(int id)
    {
        childrensRoomsGalleriesRepository.deleteById(id);
    }

}
