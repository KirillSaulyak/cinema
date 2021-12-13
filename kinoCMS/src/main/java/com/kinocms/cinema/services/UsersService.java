package com.kinocms.cinema.services;

import com.kinocms.cinema.model.UsersEntity;
import com.kinocms.cinema.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity save(UsersEntity usersEntity)
    {
        return usersRepository.save(usersEntity);
    }

    public UsersEntity findById(int id) {
        Optional<UsersEntity> usersEntity = usersRepository.findById(id);
        if (usersEntity.isPresent()) {
            return usersEntity.get();
        } else {
            return null;
        }
    }

    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }

    public void deleteById(int id) {
        usersRepository.deleteById(id);
    }
}
