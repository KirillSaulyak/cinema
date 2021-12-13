package com.kinocms.cinema.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "schedules", schema = "cino_cms", catalog = "")
public class SchedulesEntity {
    private int idSchedule;
    private Time time;
    private Date date;
    private MoviesEntity moviesByMovieFk;
    private HallsEntity hallsByHallFk;

    @Id
    @Column(name = "id_schedule", nullable = false)
    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchedulesEntity that = (SchedulesEntity) o;

        if (idSchedule != that.idSchedule) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSchedule;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "movie_fk", referencedColumnName = "id_movie", nullable = false)
    public MoviesEntity getMoviesByMovieFk() {
        return moviesByMovieFk;
    }

    public void setMoviesByMovieFk(MoviesEntity moviesByMovieFk) {
        this.moviesByMovieFk = moviesByMovieFk;
    }

    @ManyToOne
    @JoinColumn(name = "hall_fk", referencedColumnName = "id_hall", nullable = false)
    public HallsEntity getHallsByHallFk() {
        return hallsByHallFk;
    }

    public void setHallsByHallFk(HallsEntity hallsByHallFk) {
        this.hallsByHallFk = hallsByHallFk;
    }
}
