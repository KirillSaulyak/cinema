package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "advertisements_galleries", schema = "cino_cms", catalog = "")
public class AdvertisementsGalleriesEntity {
    private int idAdvertisementsGallery;
    private String pathToImage;
    private AdvertisementsEntity advertisementsByAdvertisementFk;

    public AdvertisementsGalleriesEntity() {
    }

    public AdvertisementsGalleriesEntity(String pathToImage, AdvertisementsEntity advertisementsByAdvertisementFk) {
        this.pathToImage = pathToImage;
        this.advertisementsByAdvertisementFk = advertisementsByAdvertisementFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_advertisements_gallery", nullable = false)
    public int getIdAdvertisementsGallery() {
        return idAdvertisementsGallery;
    }

    public void setIdAdvertisementsGallery(int idAdvertisementsGallery) {
        this.idAdvertisementsGallery = idAdvertisementsGallery;
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

        AdvertisementsGalleriesEntity that = (AdvertisementsGalleriesEntity) o;

        if (idAdvertisementsGallery != that.idAdvertisementsGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdvertisementsGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "advertisement_fk", referencedColumnName = "id_advertisement", nullable = false)
    public AdvertisementsEntity getAdvertisementsByAdvertisementFk() {
        return advertisementsByAdvertisementFk;
    }

    public void setAdvertisementsByAdvertisementFk(AdvertisementsEntity advertisementsByAdvertisementFk) {
        this.advertisementsByAdvertisementFk = advertisementsByAdvertisementFk;
    }
}
