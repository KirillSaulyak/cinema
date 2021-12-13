package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "mailings", schema = "cino_cms", catalog = "")
public class MailingsEntity {
    private int idMailing;
    private String pathToFile;
    private String originalName;

    public MailingsEntity() {
    }

    public MailingsEntity(String pathToFile, String originalName) {
        this.pathToFile = pathToFile;
        this.originalName = originalName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mailing", nullable = false)
    public int getIdMailing() {
        return idMailing;
    }

    public void setIdMailing(int idMailing) {
        this.idMailing = idMailing;
    }

    @Basic
    @Column(name = "path_to_file", nullable = false, length = 255)
    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Basic
    @Column(name = "original_name", nullable = false, length = 45)
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MailingsEntity that = (MailingsEntity) o;

        if (idMailing != that.idMailing) return false;
        if (pathToFile != null ? !pathToFile.equals(that.pathToFile) : that.pathToFile != null) return false;
        if (originalName != null ? !originalName.equals(that.originalName) : that.originalName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMailing;
        result = 31 * result + (pathToFile != null ? pathToFile.hashCode() : 0);
        result = 31 * result + (originalName != null ? originalName.hashCode() : 0);
        return result;
    }
}
