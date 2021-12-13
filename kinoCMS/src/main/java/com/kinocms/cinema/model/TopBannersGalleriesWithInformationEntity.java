package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "top_banners_galleries_with_information", schema = "cino_cms", catalog = "")
public class TopBannersGalleriesWithInformationEntity {
    private int idTopBannersGallery;
    private String url;
    private String description;
    private String pathToImage;
    private TopBannersEntity topBannersByTopBannersFk;

    public TopBannersGalleriesWithInformationEntity() {
    }

    public TopBannersGalleriesWithInformationEntity(String url, String description, String pathToImage,
                                                    TopBannersEntity topBannersByTopBannersFk) {
        this.url = url;
        this.description = description;
        this.pathToImage = pathToImage;
        this.topBannersByTopBannersFk = topBannersByTopBannersFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_top_banners_gallery", nullable = false)
    public int getIdTopBannersGallery() {
        return idTopBannersGallery;
    }

    public void setIdTopBannersGallery(int idTopBannersGallery) {
        this.idTopBannersGallery = idTopBannersGallery;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        TopBannersGalleriesWithInformationEntity that = (TopBannersGalleriesWithInformationEntity) o;

        if (idTopBannersGallery != that.idTopBannersGallery) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTopBannersGallery;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "top_banners_fk", referencedColumnName = "id_top_banner", nullable = false)
    public TopBannersEntity getTopBannersByTopBannersFk() {
        return topBannersByTopBannersFk;
    }

    public void setTopBannersByTopBannersFk(TopBannersEntity topBannersByTopBannersFk) {
        this.topBannersByTopBannersFk = topBannersByTopBannersFk;
    }
}
