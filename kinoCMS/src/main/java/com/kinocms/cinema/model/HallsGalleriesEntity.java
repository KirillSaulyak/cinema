package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "halls_galleries", schema = "cino_cms", catalog = "")
public class HallsGalleriesEntity {
    private int idHallsGallery;
    private String pathToImage;
    private HallsEntity hallsByHallFk;

    public HallsGalleriesEntity() {
    }

    public HallsGalleriesEntity(String pathToImage, HallsEntity hallsByHallFk) {
        this.pathToImage = pathToImage;
        this.hallsByHallFk = hallsByHallFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_halls_gallery", nullable = false)
    public int getIdHallsGallery() {
        return idHallsGallery;
    }

    public void setIdHallsGallery(int idHallsGallery) {
        this.idHallsGallery = idHallsGallery;
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

        HallsGalleriesEntity that = (HallsGalleriesEntity) o;

        if (idHallsGallery != that.idHallsGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHallsGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hall_fk", referencedColumnName = "id_hall", nullable = false)
    public HallsEntity getHallsByHallFk() {
        return hallsByHallFk;
    }

    public void setHallsByHallFk(HallsEntity hallsByHallFk) {
        this.hallsByHallFk = hallsByHallFk;
    }
}
