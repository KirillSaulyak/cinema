package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "news_galleries", schema = "cino_cms", catalog = "")
public class NewsGalleriesEntity {
    private int idNewsGallery;
    private String pathToImage;
    private NewsEntity newsByNewsFk;

    public NewsGalleriesEntity() {
    }

    public NewsGalleriesEntity(String pathToImage, NewsEntity newsByNewsFk) {
        this.pathToImage = pathToImage;
        this.newsByNewsFk = newsByNewsFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_news_gallery", nullable = false)
    public int getIdNewsGallery() {
        return idNewsGallery;
    }

    public void setIdNewsGallery(int idNewsGallery) {
        this.idNewsGallery = idNewsGallery;
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

        NewsGalleriesEntity that = (NewsGalleriesEntity) o;

        if (idNewsGallery != that.idNewsGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNewsGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "news_fk", referencedColumnName = "id_news", nullable = false)
    public NewsEntity getNewsByNewsFk() {
        return newsByNewsFk;
    }

    public void setNewsByNewsFk(NewsEntity newsByNewsFk) {
        this.newsByNewsFk = newsByNewsFk;
    }
}
