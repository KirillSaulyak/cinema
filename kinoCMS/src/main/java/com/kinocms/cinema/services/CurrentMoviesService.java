package com.kinocms.cinema.services;

import com.kinocms.cinema.model.CurrentMoviesEntity;
import com.kinocms.cinema.model.MoviesEntity;
import com.kinocms.cinema.repositories.CurrentMoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurrentMoviesService {
    @Autowired
    private CurrentMoviesRepository moviesRepository;

    public List<CurrentMoviesEntity> findAll()
    {
        return moviesRepository.findAll();
    }
    public CurrentMoviesEntity save(CurrentMoviesEntity currentMoviesEntity)
    {
        return moviesRepository.save(currentMoviesEntity);
    }
    @Transactional
    public void deleteByMoviesByMovieFk(MoviesEntity fk){moviesRepository.deleteByMoviesByMovieFk(fk);}
}
