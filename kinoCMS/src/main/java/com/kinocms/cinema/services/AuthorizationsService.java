package com.kinocms.cinema.services;

import com.kinocms.cinema.model.AuthorizationsEntity;
import com.kinocms.cinema.repositories.AuthorizationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorizationsService {
    @Autowired
    private AuthorizationsRepository authorizationsRepository;

    public AuthorizationsEntity save(AuthorizationsEntity authorizationsEntity)
    {
        return authorizationsRepository.save(authorizationsEntity);
    }

    public AuthorizationsEntity findById(int id) {
        Optional<AuthorizationsEntity> authorizationsEntity = authorizationsRepository.findById(id);
        if (authorizationsEntity.isPresent()) {
            return authorizationsEntity.get();
        } else {
            return null;
        }
    }

    public List<AuthorizationsEntity> findAll() {
        return authorizationsRepository.findAll();
    }

    public void deleteById(int id) {
        authorizationsRepository.deleteById(id);
    }
}
