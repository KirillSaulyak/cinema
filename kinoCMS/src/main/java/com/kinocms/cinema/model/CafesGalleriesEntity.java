package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "cafes_galleries", schema = "cino_cms", catalog = "")
public class CafesGalleriesEntity {
    private int idCafesGallery;
    private String pathToImage;
    private CafesEntity cafesByCafeFk;

    public CafesGalleriesEntity() {
    }

    public CafesGalleriesEntity(String pathToImage, CafesEntity cafesByCafeFk) {
        this.pathToImage = pathToImage;
        this.cafesByCafeFk = cafesByCafeFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cafes_gallery", nullable = false)
    public int getIdCafesGallery() {
        return idCafesGallery;
    }

    public void setIdCafesGallery(int idCafesGallery) {
        this.idCafesGallery = idCafesGallery;
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

        CafesGalleriesEntity that = (CafesGalleriesEntity) o;

        if (idCafesGallery != that.idCafesGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCafesGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cafe_fk", referencedColumnName = "id_cafe", nullable = false)
    public CafesEntity getCafesByCafeFk() {
        return cafesByCafeFk;
    }

    public void setCafesByCafeFk(CafesEntity cafesByCafeFk) {
        this.cafesByCafeFk = cafesByCafeFk;
    }
}
