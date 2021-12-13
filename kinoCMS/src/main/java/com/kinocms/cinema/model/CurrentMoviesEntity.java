package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "current_movies", schema = "cino_cms", catalog = "")
public class CurrentMoviesEntity {
    private int idCurrentMovie;
    private MoviesEntity moviesByMovieFk;

    public CurrentMoviesEntity() {
    }

    public CurrentMoviesEntity(MoviesEntity moviesByMovieFk) {
        this.moviesByMovieFk = moviesByMovieFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_current_movie", nullable = false)
    public int getIdCurrentMovie() {
        return idCurrentMovie;
    }

    public void setIdCurrentMovie(int idCurrentMovie) {
        this.idCurrentMovie = idCurrentMovie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentMoviesEntity that = (CurrentMoviesEntity) o;

        if (idCurrentMovie != that.idCurrentMovie) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idCurrentMovie;
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
