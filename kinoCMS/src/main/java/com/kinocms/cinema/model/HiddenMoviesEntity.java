package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "hidden_movies", schema = "cino_cms", catalog = "")
public class HiddenMoviesEntity {
    private int idHiddenMovie;
    private MoviesEntity moviesByMovieFk;

    public HiddenMoviesEntity() {
    }

    public HiddenMoviesEntity(MoviesEntity moviesByMovieFk) {
        this.moviesByMovieFk = moviesByMovieFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hidden_movie", nullable = false)
    public int getIdHiddenMovie() {
        return idHiddenMovie;
    }

    public void setIdHiddenMovie(int idHiddenMovie) {
        this.idHiddenMovie = idHiddenMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HiddenMoviesEntity that = (HiddenMoviesEntity) o;

        if (idHiddenMovie != that.idHiddenMovie) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idHiddenMovie;
    }

    @ManyToOne
    @JoinColumn(name = "movie_fk", referencedColumnName = "id_movie", nullable = false)
    public MoviesEntity getMoviesByMovieFk() {
        return moviesByMovieFk;
    }

    public void setMoviesByMovieFk(MoviesEntity moviesByMovieFk) {
        this.moviesByMovieFk = moviesByMovieFk;
    }
}
