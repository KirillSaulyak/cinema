package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "cities", schema = "cino_cms", catalog = "")
public class CitiesEntity {
    private int idCity;
    private String city;

    public CitiesEntity() {
    }

    public CitiesEntity(String city) {
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city", nullable = false)
    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitiesEntity that = (CitiesEntity) o;

        if (idCity != that.idCity) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCity;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
