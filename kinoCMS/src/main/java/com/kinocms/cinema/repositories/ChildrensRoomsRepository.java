package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.ChildrensRoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChildrensRoomsRepository extends JpaRepository<ChildrensRoomsEntity,Integer> {
    @Query(value = "SELECT * FROM childrens_rooms ORDER BY id_childrens_room LIMIT 1",nativeQuery = true)
    ChildrensRoomsEntity findFirst();
}
