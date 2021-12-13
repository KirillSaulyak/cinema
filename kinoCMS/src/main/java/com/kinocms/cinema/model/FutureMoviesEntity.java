package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "future_movies", schema = "cino_cms", catalog = "")
public class FutureMoviesEntity {
    private int idFutureMovie;
    private MoviesEntity moviesByMovieFk;

    public FutureMoviesEntity() {
    }

    public FutureMoviesEntity(MoviesEntity moviesByMovieFk) {
        this.moviesByMovieFk = moviesByMovieFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_future_movie", nullable = false)
    public int getIdFutureMovie() {
        return idFutureMovie;
    }

    public void setIdFutureMovie(int idFutureMovie) {
        this.idFutureMovie = idFutureMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FutureMoviesEntity that = (FutureMoviesEntity) o;

        if (idFutureMovie != that.idFutureMovie) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idFutureMovie;
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
