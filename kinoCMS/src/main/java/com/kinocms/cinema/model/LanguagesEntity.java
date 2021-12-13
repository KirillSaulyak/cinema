package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "languages", schema = "cino_cms", catalog = "")
public class LanguagesEntity {
    private int idLanguage;
    private String language;

    public LanguagesEntity() {
    }

    public LanguagesEntity(String language) {
        this.language = language;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_language", nullable = false)
    public int getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
    }

    @Basic
    @Column(name = "language", nullable = false, length = 45)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguagesEntity that = (LanguagesEntity) o;

        if (idLanguage != that.idLanguage) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLanguage;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
