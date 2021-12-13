package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "genders", schema = "cino_cms", catalog = "")
public class GendersEntity {
    private int idGender;
    private String gender;

    public GendersEntity() {
    }

    public GendersEntity(String gender) {
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gender", nullable = false)
    public int getIdGender() {
        return idGender;
    }

    public void setIdGender(int idGender) {
        this.idGender = idGender;
    }

    @Basic
    @Column(name = "gender", nullable = false, length = 45)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GendersEntity that = (GendersEntity) o;

        if (idGender != that.idGender) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGender;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
