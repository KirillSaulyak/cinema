package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "admins", schema = "cino_cms", catalog = "")
public class AdminsEntity {
    private int idAdmin;
    private AuthorizationsEntity authorizationsByAuthorizationFk;

    public AdminsEntity() {
    }

    @Id
    @Column(name = "id_admin", nullable = false)
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminsEntity that = (AdminsEntity) o;

        if (idAdmin != that.idAdmin) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idAdmin;
    }

    @ManyToOne
    @JoinColumn(name = "authorization_fk", referencedColumnName = "id_authorization", nullable = false)
    public AuthorizationsEntity getAuthorizationsByAuthorizationFk() {
        return authorizationsByAuthorizationFk;
    }

    public void setAuthorizationsByAuthorizationFk(AuthorizationsEntity authorizationsByAuthorizationFk) {
        this.authorizationsByAuthorizationFk = authorizationsByAuthorizationFk;
    }
}
