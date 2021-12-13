package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "description_current_cinemas_galleries", schema = "cino_cms", catalog = "")
public class DescriptionCurrentCinemasGalleriesEntity {
    private int idDescriptionCurrentCinemasGallery;
    private String pathToImage;
    private DescriptionCurrentCinemasEntity descriptionCurrentCinemasByDescriptionCurrentCinemaFk;

    public DescriptionCurrentCinemasGalleriesEntity() {
    }

    public DescriptionCurrentCinemasGalleriesEntity(String pathToImage, DescriptionCurrentCinemasEntity descriptionCurrentCinemasByDescriptionCurrentCinemaFk) {
        this.pathToImage = pathToImage;
        this.descriptionCurrentCinemasByDescriptionCurrentCinemaFk = descriptionCurrentCinemasByDescriptionCurrentCinemaFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_description_current_cinemas_gallery", nullable = false)
    public int getIdDescriptionCurrentCinemasGallery() {
        return idDescriptionCurrentCinemasGallery;
    }

    public void setIdDescriptionCurrentCinemasGallery(int idDescriptionCurrentCinemasGallery) {
        this.idDescriptionCurrentCinemasGallery = idDescriptionCurrentCinemasGallery;
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

        DescriptionCurrentCinemasGalleriesEntity that = (DescriptionCurrentCinemasGalleriesEntity) o;

        if (idDescriptionCurrentCinemasGallery != that.idDescriptionCurrentCinemasGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDescriptionCurrentCinemasGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "description_current_cinema_fk", referencedColumnName = "id_description_current_cinema", nullable = false)
    public DescriptionCurrentCinemasEntity getDescriptionCurrentCinemasByDescriptionCurrentCinemaFk() {
        return descriptionCurrentCinemasByDescriptionCurrentCinemaFk;
    }

    public void setDescriptionCurrentCinemasByDescriptionCurrentCinemaFk(DescriptionCurrentCinemasEntity descriptionCurrentCinemasByDescriptionCurrentCinemaFk) {
        this.descriptionCurrentCinemasByDescriptionCurrentCinemaFk = descriptionCurrentCinemasByDescriptionCurrentCinemaFk;
    }
}
