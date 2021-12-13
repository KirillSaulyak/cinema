package com.kinocms.cinema.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "news", schema = "cino_cms", catalog = "")
public class NewsEntity {
    private int idNews;
    private String name;
    private String description;
    private Date publicationDate;
    private Date creationDate;
    private String pathToMainImage;
    private String linkToVideo;
    private boolean enabled;

    public NewsEntity() {
    }

    public NewsEntity(String name, String description, Date publicationDate, Date creationDate, String pathToMainImage, String linkToVideo, boolean enabled) {
        this.name = name;
        this.description = description;
        this.publicationDate = publicationDate;
        this.creationDate = creationDate;
        this.pathToMainImage = pathToMainImage;
        this.linkToVideo = linkToVideo;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_news", nullable = false)
    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "publication_date", nullable = false)
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "path_to_main_image", nullable = false, length = 255)
    public String getPathToMainImage() {
        return pathToMainImage;
    }

    public void setPathToMainImage(String pathToMainImage) {
        this.pathToMainImage = pathToMainImage;
    }

    @Basic
    @Column(name = "link_to_video", nullable = false, length = 255)
    public String getLinkToVideo() {
        return linkToVideo;
    }

    public void setLinkToVideo(String linkToVideo) {
        this.linkToVideo = linkToVideo;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsEntity that = (NewsEntity) o;

        if (idNews != that.idNews) return false;
        if (enabled != that.enabled) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (publicationDate != null ? !publicationDate.equals(that.publicationDate) : that.publicationDate != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (pathToMainImage != null ? !pathToMainImage.equals(that.pathToMainImage) : that.pathToMainImage != null)
            return false;
        if (linkToVideo != null ? !linkToVideo.equals(that.linkToVideo) : that.linkToVideo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNews;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (pathToMainImage != null ? pathToMainImage.hashCode() : 0);
        result = 31 * result + (linkToVideo != null ? linkToVideo.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
