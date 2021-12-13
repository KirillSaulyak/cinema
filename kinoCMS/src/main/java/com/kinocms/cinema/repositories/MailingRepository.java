package com.kinocms.cinema.repositories;

import com.kinocms.cinema.model.MailingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MailingRepository extends JpaRepository<MailingsEntity,Integer> {
    @Query(nativeQuery = true, value = "Select count(id_mailing) from mailings")
    Integer getCountMailings();

    @Query(nativeQuery = true, value = "Select min(id_mailing) from mailings")
    Integer getMinId();
}
