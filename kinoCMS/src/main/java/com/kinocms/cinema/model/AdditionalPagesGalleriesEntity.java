package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "additional_pages_galleries", schema = "cino_cms", catalog = "")
public class AdditionalPagesGalleriesEntity {
    private int idAdditionalPagesGallery;
    private String pathToImage;
    private AdditionalPagesEntity additionalPagesByAdditionalPageFk;

    public AdditionalPagesGalleriesEntity() {
    }

    public AdditionalPagesGalleriesEntity(String pathToImage, AdditionalPagesEntity additionalPagesByAdditionalPageFk) {
        this.pathToImage = pathToImage;
        this.additionalPagesByAdditionalPageFk = additionalPagesByAdditionalPageFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_additional_pages_gallery", nullable = false)
    public int getIdAdditionalPagesGallery() {
        return idAdditionalPagesGallery;
    }

    public void setIdAdditionalPagesGallery(int idAdditionalPagesGallery) {
        this.idAdditionalPagesGallery = idAdditionalPagesGallery;
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

        AdditionalPagesGalleriesEntity that = (AdditionalPagesGalleriesEntity) o;

        if (idAdditionalPagesGallery != that.idAdditionalPagesGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdditionalPagesGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "additional_page_fk", referencedColumnName = "id_additional_page", nullable = false)
    public AdditionalPagesEntity getAdditionalPagesByAdditionalPageFk() {
        return additionalPagesByAdditionalPageFk;
    }

    public void setAdditionalPagesByAdditionalPageFk(AdditionalPagesEntity additionalPagesByAdditionalPageFk) {
        this.additionalPagesByAdditionalPageFk = additionalPagesByAdditionalPageFk;
    }
}
