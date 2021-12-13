package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "movies", schema = "cino_cms", catalog = "")
public class MoviesEntity {
    private int idMovie;
    private String name;
    private String description;
    private String pathToMainImage;
    private String trailerLink;

    public MoviesEntity() {
    }

    public MoviesEntity(String name, String description, String pathToMainImage, String trailerLink) {
        this.name = name;
        this.description = description;
        this.pathToMainImage = pathToMainImage;
        this.trailerLink = trailerLink;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie", nullable = false)
    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "path_to_main_image", nullable = false, length = 255)
    public String getPathToMainImage() {
        return pathToMainImage;
    }

    public void setPathToMainImage(String pathToMainImage) {
        this.pathToMainImage = pathToMainImage;
    }

    @Basic
    @Column(name = "trailer_link", nullable = false, length = 255)
    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesEntity that = (MoviesEntity) o;

        if (idMovie != that.idMovie) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (trailerLink != null ? !trailerLink.equals(that.trailerLink) : that.trailerLink != null) return false;
        if (pathToMainImage != null ? !pathToMainImage.equals(that.pathToMainImage) : that.pathToMainImage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMovie;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (trailerLink != null ? trailerLink.hashCode() : 0);
        result = 31 * result + (pathToMainImage != null ? pathToMainImage.hashCode() : 0);
        return result;
    }
}
