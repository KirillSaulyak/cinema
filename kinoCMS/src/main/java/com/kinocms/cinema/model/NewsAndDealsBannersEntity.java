package com.kinocms.cinema.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "news_and_deals_banners", schema = "cino_cms", catalog = "")
public class NewsAndDealsBannersEntity {
    private int idNewsAndDealsBanner;
    private boolean enabled;
    private int rotationSpeed;

    public NewsAndDealsBannersEntity() {
    }

    public NewsAndDealsBannersEntity(boolean enabled, int rotationSpeed) {
        this.enabled = enabled;
        this.rotationSpeed = rotationSpeed;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_news_and_deals_banner", nullable = false)
    public int getIdNewsAndDealsBanner() {
        return idNewsAndDealsBanner;
    }

    public void setIdNewsAndDealsBanner(int idNewsAndDealsBanner) {
        this.idNewsAndDealsBanner = idNewsAndDealsBanner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsAndDealsBannersEntity)) return false;
        NewsAndDealsBannersEntity that = (NewsAndDealsBannersEntity) o;
        return idNewsAndDealsBanner == that.idNewsAndDealsBanner && enabled == that.enabled && rotationSpeed == that.rotationSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNewsAndDealsBanner, enabled, rotationSpeed);
    }

    @Basic
    @Column(name = "rotation_speed", nullable = false)
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}
