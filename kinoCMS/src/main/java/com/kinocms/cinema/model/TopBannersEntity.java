package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "top_banners", schema = "cino_cms", catalog = "")
public class TopBannersEntity {
    private int idTopBanner;
    private int rotationSpeed;
    private boolean enabled;

    public TopBannersEntity() {
    }

    public TopBannersEntity(int rotationSpeed, boolean enabled) {
        this.rotationSpeed = rotationSpeed;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_top_banner", nullable = false)
    public int getIdTopBanner() {
        return idTopBanner;
    }

    public void setIdTopBanner(int idTopBanner) {
        this.idTopBanner = idTopBanner;
    }

    @Basic
    @Column(name = "rotation_speed", nullable = false)
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopBannersEntity that = (TopBannersEntity) o;

        if (idTopBanner != that.idTopBanner) return false;
        if (rotationSpeed != that.rotationSpeed) return false;
        if (enabled != that.enabled) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTopBanner;
        result = 31 * result + rotationSpeed;
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
