package com.kinocms.cinema.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "halls", schema = "cino_cms", catalog = "")
public class HallsEntity {
    private int idHall;
    private String name;
    private String description;
    private String pathToHallLayout;
    private String pathToTopBanner;
    private Date creationDate;
    private CinemasEntity cinemasByCinemaFk;

    public HallsEntity() {
    }

    public HallsEntity(String name, String description, String pathToHallLayout, String pathToTopBanner,
                       Date creationDate, CinemasEntity cinemasByCinemaFk) {
        this.name = name;
        this.description = description;
        this.pathToHallLayout = pathToHallLayout;
        this.pathToTopBanner = pathToTopBanner;
        this.creationDate = creationDate;
        this.cinemasByCinemaFk = cinemasByCinemaFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hall", nullable = false)
    public int getIdHall() {
        return idHall;
    }

    public void setIdHall(int idHall) {
        this.idHall = idHall;
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
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "path_to_hall_layout", nullable = false, length = 255)
    public String getPathToHallLayout() {
        return pathToHallLayout;
    }

    public void setPathToHallLayout(String pathToHallLayout) {
        this.pathToHallLayout = pathToHallLayout;
    }

    @Basic
    @Column(name = "path_to_top_banner", nullable = false, length = 255)
    public String getPathToTopBanner() {
        return pathToTopBanner;
    }

    public void setPathToTopBanner(String pathToTopBanner) {
        this.pathToTopBanner = pathToTopBanner;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne
    @JoinColumn(name = "cinema_fk", referencedColumnName = "id_cinema", nullable = false)
    public CinemasEntity getCinemasByCinemaFk() {
        return cinemasByCinemaFk;
    }

    public void setCinemasByCinemaFk(CinemasEntity cinemasByCinemaFk) {
        this.cinemasByCinemaFk = cinemasByCinemaFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HallsEntity that = (HallsEntity) o;

        if (idHall != that.idHall) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (pathToHallLayout != null ? !pathToHallLayout.equals(that.pathToHallLayout) : that.pathToHallLayout != null)
            return false;
        if (pathToTopBanner != null ? !pathToTopBanner.equals(that.pathToTopBanner) : that.pathToTopBanner != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHall;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pathToHallLayout != null ? pathToHallLayout.hashCode() : 0);
        result = 31 * result + (pathToTopBanner != null ? pathToTopBanner.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
