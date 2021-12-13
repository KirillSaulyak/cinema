package com.kinocms.cinema.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "description_current_cinemas", schema = "cino_cms", catalog = "")
public class DescriptionCurrentCinemasEntity {
    private int idDescriptionCurrentCinema;
    private String name;
    private String description;
    private String pathToMainImage;
    private boolean enabled;
    private Date creationDate;

    public DescriptionCurrentCinemasEntity() {
        this.name = "О кинотеатре";
        this.enabled = false;
        this.creationDate =  new Date(new java.util.Date().getTime());
    }

    public DescriptionCurrentCinemasEntity(String description, String pathToMainImage, boolean enabled) {
        this.description = description;
        this.pathToMainImage = pathToMainImage;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_description_current_cinema", nullable = false)
    public int getIdDescriptionCurrentCinema() {
        return idDescriptionCurrentCinema;
    }

    public void setIdDescriptionCurrentCinema(int idDescriptionCurrentCinema) {
        this.idDescriptionCurrentCinema = idDescriptionCurrentCinema;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "path_to_main_image", nullable = true, length = 255)
    public String getPathToMainImage() {
        return pathToMainImage;
    }

    public void setPathToMainImage(String pathToMainImage) {
        this.pathToMainImage = pathToMainImage;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DescriptionCurrentCinemasEntity that = (DescriptionCurrentCinemasEntity) o;

        if (idDescriptionCurrentCinema != that.idDescriptionCurrentCinema) return false;
        if (enabled != that.enabled) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (pathToMainImage != null ? !pathToMainImage.equals(that.pathToMainImage) : that.pathToMainImage != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDescriptionCurrentCinema;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pathToMainImage != null ? pathToMainImage.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
