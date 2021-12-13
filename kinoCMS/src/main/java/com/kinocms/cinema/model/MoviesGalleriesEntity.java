package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "movies_galleries", schema = "cino_cms", catalog = "")
public class MoviesGalleriesEntity {
    private int idMoviesGallery;
    private String pathToImage;
    private MoviesEntity moviesByMovieFk;

    public MoviesGalleriesEntity() {
    }

    public MoviesGalleriesEntity(String pathToImage, MoviesEntity moviesByMovieFk) {
        this.pathToImage = pathToImage;
        this.moviesByMovieFk = moviesByMovieFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movies_gallery", nullable = false)
    public int getIdMoviesGallery() {
        return idMoviesGallery;
    }

    public void setIdMoviesGallery(int idMoviesGallery) {
        this.idMoviesGallery = idMoviesGallery;
    }

    @Basic
    @Column(name = "path_to_image", nullable = false, length = 255)
    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesGalleriesEntity that = (MoviesGalleriesEntity) o;

        if (idMoviesGallery != that.idMoviesGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMoviesGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
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
