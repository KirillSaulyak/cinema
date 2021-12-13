package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "news_and_deals_banners_galleries_with_information", schema = "cino_cms", catalog = "")
public class NewsAndDealsBannersGalleriesWithInformationEntity {
    private int idNewsAndDealsBannersGalleries;
    private String url;
    private String pathToImage;
    private NewsAndDealsBannersEntity newsAndDealsBannersByNewsAndDealsBannersFk;

    public NewsAndDealsBannersGalleriesWithInformationEntity() {
    }

    public NewsAndDealsBannersGalleriesWithInformationEntity(String url, String pathToImage, NewsAndDealsBannersEntity newsAndDealsBannersByNewsAndDealsBannersFk) {
        this.url = url;
        this.pathToImage = pathToImage;
        this.newsAndDealsBannersByNewsAndDealsBannersFk = newsAndDealsBannersByNewsAndDealsBannersFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_news_and_deals_banners_galleries", nullable = false)
    public int getIdNewsAndDealsBannersGalleries() {
        return idNewsAndDealsBannersGalleries;
    }

    public void setIdNewsAndDealsBannersGalleries(int idNewsAndDealsBannersGalleries) {
        this.idNewsAndDealsBannersGalleries = idNewsAndDealsBannersGalleries;
    }

    @Basic
    @Column(name = "url", length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

        NewsAndDealsBannersGalleriesWithInformationEntity that = (NewsAndDealsBannersGalleriesWithInformationEntity) o;

        if (idNewsAndDealsBannersGalleries != that.idNewsAndDealsBannersGalleries) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNewsAndDealsBannersGalleries;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "news_and_deals_banners_fk", referencedColumnName = "id_news_and_deals_banner", nullable = false)
    public NewsAndDealsBannersEntity getNewsAndDealsBannersByNewsAndDealsBannersFk() {
        return newsAndDealsBannersByNewsAndDealsBannersFk;
    }

    public void setNewsAndDealsBannersByNewsAndDealsBannersFk(NewsAndDealsBannersEntity newsAndDealsBannersByNewsAndDealsBannersFk) {
        this.newsAndDealsBannersByNewsAndDealsBannersFk = newsAndDealsBannersByNewsAndDealsBannersFk;
    }
}
