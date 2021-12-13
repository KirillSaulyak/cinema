package com.kinocms.cinema.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "background_banners", schema = "cino_cms", catalog = "")
public class BackgroundBannersEntity {
    private int idBackgroundBanner;
    private String pathToBackgroundBanner;
    private boolean enabled;

    public BackgroundBannersEntity() {
    }

    public BackgroundBannersEntity(String pathToBackgroundBanner, boolean enabled) {
        this.pathToBackgroundBanner = pathToBackgroundBanner;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_background_banner", nullable = false)
    public int getIdBackgroundBanner() {
        return idBackgroundBanner;
    }

    public void setIdBackgroundBanner(int idBackgroundBanner) {
        this.idBackgroundBanner = idBackgroundBanner;
    }

    @Basic
    @Column(name = "path_to_background_banner", nullable = false, length = 255)
    public String getPathToBackgroundBanner() {
        return pathToBackgroundBanner;
    }

    public void setPathToBackgroundBanner(String pathToBackgroundBanner) {
        this.pathToBackgroundBanner = pathToBackgroundBanner;
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
        if (!(o instanceof BackgroundBannersEntity)) return false;
        BackgroundBannersEntity that = (BackgroundBannersEntity) o;
        return idBackgroundBanner == that.idBackgroundBanner && enabled == that.enabled && pathToBackgroundBanner.equals(that.pathToBackgroundBanner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBackgroundBanner, pathToBackgroundBanner, enabled);
    }
}
