package com.kinocms.cinema.services;

import com.kinocms.cinema.model.FutureMoviesEntity;
import com.kinocms.cinema.model.MoviesEntity;
import com.kinocms.cinema.repositories.FutureMoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FutureMoviesService{
    @Autowired
    private FutureMoviesRepository futureMoviesRepository;

    public List<FutureMoviesEntity> findAll()
    {
        return futureMoviesRepository.findAll();
    }

    public FutureMoviesEntity save(FutureMoviesEntity futureMoviesEntity)
    {
        return futureMoviesRepository.save(futureMoviesEntity);
    }
    @Transactional
    public void deleteByMoviesByMovieFk(MoviesEntity fk){futureMoviesRepository.deleteByMoviesByMovieFk(fk);}
}
