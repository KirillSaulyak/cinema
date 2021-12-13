package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "cinemas_galleries", schema = "cino_cms", catalog = "")
public class CinemasGalleriesEntity {
    private int idCinemasImage;
    private String pathToImage;
    private CinemasEntity cinemasByCinemaFk;

    public CinemasGalleriesEntity() {
    }

    public CinemasGalleriesEntity(String pathToImage, CinemasEntity cinemasByCinemaFk) {
        this.pathToImage = pathToImage;
        this.cinemasByCinemaFk = cinemasByCinemaFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cinemas_image", nullable = false)
    public int getIdCinemasImage() {
        return idCinemasImage;
    }

    public void setIdCinemasImage(int idCinemasImage) {
        this.idCinemasImage = idCinemasImage;
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

        CinemasGalleriesEntity that = (CinemasGalleriesEntity) o;

        if (idCinemasImage != that.idCinemasImage) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCinemasImage;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cinema_fk", referencedColumnName = "id_cinema", nullable = false)
    public CinemasEntity getCinemasByCinemaFk() {
        return cinemasByCinemaFk;
    }

    public void setCinemasByCinemaFk(CinemasEntity cinemasByCinemaFk) {
        this.cinemasByCinemaFk = cinemasByCinemaFk;
    }
}
