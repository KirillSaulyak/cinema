package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "cinemas", schema = "cino_cms", catalog = "")
public class CinemasEntity {
    private int idCinema;
    private String name;
    private String description;
    private String conditions;
    private String pathToLogo;
    private String pathToTopBanner;

    public CinemasEntity() {
    }

    public CinemasEntity(String name, String description, String conditions, String pathToLogo, String pathToTopBanner) {
        this.name = name;
        this.description = description;
        this.conditions = conditions;
        this.pathToLogo = pathToLogo;
        this.pathToTopBanner = pathToTopBanner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cinema", nullable = false)
    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "conditions", nullable = false, length = -1)
    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    @Basic
    @Column(name = "path_to_logo", nullable = false, length = 255)
    public String getPathToLogo() {
        return pathToLogo;
    }

    public void setPathToLogo(String pathToLogo) {
        this.pathToLogo = pathToLogo;
    }

    @Basic
    @Column(name = "path_to_top_banner", nullable = false, length = 255)
    public String getPathToTopBanner() {
        return pathToTopBanner;
    }

    public void setPathToTopBanner(String pathToTopBanner) {
        this.pathToTopBanner = pathToTopBanner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CinemasEntity that = (CinemasEntity) o;

        if (idCinema != that.idCinema) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (conditions != null ? !conditions.equals(that.conditions) : that.conditions != null) return false;
        if (pathToLogo != null ? !pathToLogo.equals(that.pathToLogo) : that.pathToLogo != null) return false;
        if (pathToTopBanner != null ? !pathToTopBanner.equals(that.pathToTopBanner) : that.pathToTopBanner != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCinema;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (conditions != null ? conditions.hashCode() : 0);
        result = 31 * result + (pathToLogo != null ? pathToLogo.hashCode() : 0);
        result = 31 * result + (pathToTopBanner != null ? pathToTopBanner.hashCode() : 0);
        return result;
    }
}
