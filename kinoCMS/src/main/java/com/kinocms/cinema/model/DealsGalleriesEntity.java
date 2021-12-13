package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "deals_galleries", schema = "cino_cms", catalog = "")
public class DealsGalleriesEntity {
    private int idDealsGallery;
    private String pathToImage;
    private DealsEntity dealsByDealFk;

    public DealsGalleriesEntity() {
    }

    public DealsGalleriesEntity(String pathToImage, DealsEntity dealsByDealFk) {
        this.pathToImage = pathToImage;
        this.dealsByDealFk = dealsByDealFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deals_gallery", nullable = false)
    public int getIdDealsGallery() {
        return idDealsGallery;
    }

    public void setIdDealsGallery(int idDealsGallery) {
        this.idDealsGallery = idDealsGallery;
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

        DealsGalleriesEntity that = (DealsGalleriesEntity) o;

        if (idDealsGallery != that.idDealsGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDealsGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "deal_fk", referencedColumnName = "id_deal", nullable = false)
    public DealsEntity getDealsByDealFk() {
        return dealsByDealFk;
    }

    public void setDealsByDealFk(DealsEntity dealsByDealFk) {
        this.dealsByDealFk = dealsByDealFk;
    }
}
