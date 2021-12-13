package com.kinocms.cinema.сontrollers.kinocms.generalRequest.moviesRequest;

import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class UserMoviesRestController {
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private MoviesGalleriesService moviesGalleriesService;
    @Autowired
    private CurrentMoviesService currentMoviesService;
    @Autowired
    private FutureMoviesService futureMoviesService;
    @Autowired
    private MoviesTypeService moviesTypeService;

    @PostMapping("/send-all-movies-information")
    public Map<String, String> sendMoviesInformation() {
        Gson gson = new Gson();
        Map<String, String> moviesInformation = new HashMap<>();
        MoviesEntity movieTemp;

        List<CurrentMoviesEntity> findCurrentMovies = currentMoviesService.findAll();
        List<MoviesEntity> movies = new LinkedList<>();
        List<List<MoviesTypesEntity>> moviesTypes = new LinkedList<>();
        for (var movie : findCurrentMovies) {
            movieTemp = moviesService.findById(movie.getMoviesByMovieFk().getIdMovie());
            movies.add(movieTemp);
            moviesTypes.add(moviesTypeService.findByMoviesByMovieFk(movieTemp));
        }
        moviesInformation.put("currentMovies", gson.toJson(movies));
        moviesInformation.put("currentMoviesTypes", gson.toJson(moviesTypes));

        List<FutureMoviesEntity> findFutureMovies = futureMoviesService.findAll();
        movies.clear();
        moviesTypes.clear();
        for (var movie : findFutureMovies) {
            movieTemp = moviesService.findById(movie.getMoviesByMovieFk().getIdMovie());
            movies.add(movieTemp);
            moviesTypes.add(moviesTypeService.findByMoviesByMovieFk(movieTemp));
        }
        moviesInformation.put("futureMovies", gson.toJson(movies));
        moviesInformation.put("futureMoviesTypes", gson.toJson(moviesTypes));
        return moviesInformation;
    }

    @PostMapping("/send-chosen-movie-information")
    public Map<String, String> sendChosenMovieInformation(@RequestParam("idMovie") Integer idMovie) {
        Gson gson = new Gson();
        Map<String, String> movies = new HashMap<>();

        MoviesEntity moviesEntity = moviesService.findById(idMovie);
        movies.put("movie", gson.toJson(moviesEntity));

        movies.put("moviesTypes", gson.toJson(moviesTypeService.findByMoviesByMovieFk(moviesEntity)));

        List<MoviesGalleriesEntity> moviesGallery = moviesGalleriesService.findByMoviesByMovieFk(moviesEntity);
        movies.put("moviesGallery", gson.toJson(moviesGallery));

        return movies;
    }
}
