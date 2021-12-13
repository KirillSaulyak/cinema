package com.kinocms.cinema.сontrollers.admin.movie.moviesPage;

import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class MoviesController {
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private MoviesGalleriesService moviesGalleriesService;
    @Autowired
    private HiddenMoviesService hiddenMoviesService;
    @Autowired
    private CurrentMoviesService currentMoviesService;
    @Autowired
    private FutureMoviesService futureMoviesService;
    @Autowired
    private MoviesTypeService moviesTypeService;

    @GetMapping("/movies")
    public String showMoviesPage(Model model) {
        model.addAttribute("linkToCreateMoviesPage", "/admin/create-movie");

        List<HiddenMoviesEntity> hiddenMoviesEntities = hiddenMoviesService.findAll();
        if (hiddenMoviesEntities.size() > 0) {
            LinkedList<Integer> idHiddenMovies = new LinkedList<>();
            LinkedList<String> pathsToMainImageHiddenMovies = new LinkedList<>();
            LinkedList<String> nameHiddenMovies = new LinkedList<>();
            for (var hiddenMovie : hiddenMoviesEntities) {
                idHiddenMovies.add(hiddenMovie.getMoviesByMovieFk().getIdMovie());
                nameHiddenMovies.add(hiddenMovie.getMoviesByMovieFk().getName());
                pathsToMainImageHiddenMovies.add(hiddenMovie.getMoviesByMovieFk().getPathToMainImage());
            }

            model.addAttribute("idHiddenMovies", idHiddenMovies);
            model.addAttribute("nameHiddenMovies", nameHiddenMovies);
            model.addAttribute("imageHiddenMovies", pathsToMainImageHiddenMovies);
        }

        List<CurrentMoviesEntity> currentMoviesEntities = currentMoviesService.findAll();
        if (currentMoviesEntities.size() > 0) {
            LinkedList<Integer> idCurrentMovies = new LinkedList<>();
            LinkedList<String> nameCurrentMovies = new LinkedList<>();
            LinkedList<String> pathsToMainCurrentMovies = new LinkedList<>();

            for (var currentMovie : currentMoviesEntities) {
                idCurrentMovies.add(currentMovie.getMoviesByMovieFk().getIdMovie());
                nameCurrentMovies.add(currentMovie.getMoviesByMovieFk().getName());
                pathsToMainCurrentMovies.add(currentMovie.getMoviesByMovieFk().getPathToMainImage());
            }

            model.addAttribute("idCurrentMovies", idCurrentMovies);
            model.addAttribute("nameCurrentMovies", nameCurrentMovies);
            model.addAttribute("imageCurrentMovies", pathsToMainCurrentMovies);
        }

        List<FutureMoviesEntity> futureMoviesEntities = futureMoviesService.findAll();
        if (futureMoviesEntities.size() > 0) {
            LinkedList<Integer> idFutureMovies = new LinkedList<>();
            LinkedList<String> nameFutureMovies = new LinkedList<>();
            LinkedList<String> pathsToMainImageFutureMovies = new LinkedList<>();

            for (var futureMovie : futureMoviesEntities) {
                idFutureMovies.add(futureMovie.getMoviesByMovieFk().getIdMovie());
                nameFutureMovies.add(futureMovie.getMoviesByMovieFk().getName());
                pathsToMainImageFutureMovies.add(futureMovie.getMoviesByMovieFk().getPathToMainImage());
            }

            model.addAttribute("idFutureMovies", idFutureMovies);
            model.addAttribute("nameFutureMovies", nameFutureMovies);
            model.addAttribute("imageFutureMovies", pathsToMainImageFutureMovies);
        }

        return "admin/movies/MoviesPage";
    }

    @GetMapping("/movies-change")
    public String editMovie(@RequestParam("moviesId") Integer id, Model model) {
        MoviesEntity moviesEntity = moviesService.findById(id);

        model.addAttribute("moviesId", moviesEntity.getIdMovie());
        model.addAttribute("moviesName", moviesEntity.getName());
        LinkedList<String> moviesTypes = new LinkedList<>();
        LinkedList<MoviesTypesEntity> moviesTypesEntities = moviesTypeService.findByMoviesByMovieFk(moviesEntity);
        for (var moviesType : moviesTypesEntities) {
            moviesTypes.add(moviesType.getType());
        }
        model.addAttribute("moviesTypes", moviesTypes);
        model.addAttribute("moviesDescription", moviesEntity.getDescription());
        model.addAttribute("moviesTrailerLink", moviesEntity.getTrailerLink());

        model.addAttribute("moviesMainImage", moviesEntity.getPathToMainImage());

        LinkedList<String> pathToImages = new LinkedList<>();
        LinkedList<MoviesGalleriesEntity> moviesGalleriesEntities = moviesGalleriesService.findByMoviesByMovieFk(moviesEntity);
        for (var path : moviesGalleriesEntities) {
            pathToImages.add(path.getPathToImage());
        }

        model.addAttribute("moviesGallery", pathToImages);
        return "admin/movies/CreateMoviesPage";
    }

}
