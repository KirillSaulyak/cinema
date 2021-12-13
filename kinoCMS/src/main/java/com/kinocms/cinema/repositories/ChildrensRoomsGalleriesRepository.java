package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.ChildrensRoomsEntity;
import com.kinocms.cinema.model.ChildrensRoomsGalleriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildrensRoomsGalleriesRepository extends JpaRepository<ChildrensRoomsGalleriesEntity,Integer> {
    List<ChildrensRoomsGalleriesEntity> findByChildrensRoomsByChildrensRoomFk(ChildrensRoomsEntity childrensRoomsEntity);
}
