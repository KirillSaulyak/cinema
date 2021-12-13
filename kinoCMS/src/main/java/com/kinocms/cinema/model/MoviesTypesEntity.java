package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "movies_types", schema = "cino_cms", catalog = "")
public class MoviesTypesEntity {
    private int idType;
    private String type;
    private MoviesEntity moviesByMovieFk;

    public MoviesTypesEntity() {
    }

    public MoviesTypesEntity(String type, MoviesEntity moviesByMovieFk) {
        this.type = type;
        this.moviesByMovieFk = moviesByMovieFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type", nullable = false)
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesTypesEntity that = (MoviesTypesEntity) o;

        if (idType != that.idType) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idType;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
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
