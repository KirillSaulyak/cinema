package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "authorizations", schema = "cino_cms", catalog = "")
public class AuthorizationsEntity {
    private int idAuthorization;
    private String eMail;
    private String password;

    public AuthorizationsEntity() {
    }

    public AuthorizationsEntity(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_authorization", nullable = false)
    public int getIdAuthorization() {
        return idAuthorization;
    }

    public void setIdAuthorization(int idAuthorization) {
        this.idAuthorization = idAuthorization;
    }

    @Basic
    @Column(name = "e_mail", nullable = false, length = 45)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorizationsEntity that = (AuthorizationsEntity) o;

        if (idAuthorization != that.idAuthorization) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAuthorization;
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
