package com.kinocms.cinema.сontrollers.admin.movie.moviesPage;

import com.kinocms.cinema.filesManipulation.DeleteFile;
import com.kinocms.cinema.filesManipulation.FileToDisk;
import com.kinocms.cinema.filesManipulation.TypesFile;
import com.kinocms.cinema.filesManipulation.TypesGallery;
import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;


@RestController
@RequestMapping("/admin")
public class MoviesRestController {
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

    @DeleteMapping("/movies-delete")
    public void deleteMovie(@RequestParam("moviesId") Integer id) {
        MoviesEntity moviesEntity = moviesService.findById(id);

        LinkedList<MoviesGalleriesEntity> moviesGalleriesEntities = moviesGalleriesService.findByMoviesByMovieFk(moviesEntity);
        for (var picture : moviesGalleriesEntities) {
            DeleteFile.delete(picture.getPathToImage());
        }

        DeleteFile.delete(moviesEntity.getPathToMainImage());

        DeleteFile.delete(FileToDisk.NAME_ROOT_FOLDER + "/" +TypesFile.IMAGE.getNameFolder() + "/"
                + TypesGallery.MOVIES + "/" + moviesEntity.getName());

        moviesService.deleteById(id);
    }

    @PostMapping("/change-movies-state")
    public void changeStateMovie(@RequestParam("moviesId") Integer id, @RequestParam("moviesOldState") String oldState,
                                 @RequestParam("moviesNewState") String newState) {
        if (!oldState.contains(newState)) {
            switch (oldState) {
                case "HiddenMoviesList":
                    hiddenMoviesService.deleteByMoviesByMovieFk(moviesService.findById(id));
                    break;
                case "CurrentMoviesList":
                    currentMoviesService.deleteByMoviesByMovieFk(moviesService.findById(id));
                    break;
                case "FutureMoviesList":
                    futureMoviesService.deleteByMoviesByMovieFk(moviesService.findById(id));
                    break;
            }

            switch (newState) {
                case "HiddenMoviesList":
                    hiddenMoviesService.save(new HiddenMoviesEntity(moviesService.findById(id)));
                    break;
                case "CurrentMoviesList":
                    currentMoviesService.save(new CurrentMoviesEntity(moviesService.findById(id)));
                    break;
                case "FutureMoviesList":
                    futureMoviesService.save(new FutureMoviesEntity(moviesService.findById(id)));
                    break;
            }
        }
    }

}
