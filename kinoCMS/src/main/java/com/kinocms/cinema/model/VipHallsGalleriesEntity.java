package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "vip_halls_galleries", schema = "cino_cms", catalog = "")
public class VipHallsGalleriesEntity {
    private int idVipHallsGallery;
    private String pathToImage;
    private VipHallsEntity vipHallsByVipHallFk;

    public VipHallsGalleriesEntity() {
    }

    public VipHallsGalleriesEntity(String pathToImage, VipHallsEntity vipHallsByVipHallFk) {
        this.pathToImage = pathToImage;
        this.vipHallsByVipHallFk = vipHallsByVipHallFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vip_halls_gallery", nullable = false)
    public int getIdVipHallsGallery() {
        return idVipHallsGallery;
    }

    public void setIdVipHallsGallery(int idVipHallsGallery) {
        this.idVipHallsGallery = idVipHallsGallery;
    }

    @Basic
    @Column(name = "path_to_image", nullable = false, length = 255)
    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VipHallsGalleriesEntity that = (VipHallsGalleriesEntity) o;

        if (idVipHallsGallery != that.idVipHallsGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVipHallsGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "vip_hall_fk", referencedColumnName = "id_vip_hall", nullable = false)
    public VipHallsEntity getVipHallsByVipHallFk() {
        return vipHallsByVipHallFk;
    }

    public void setVipHallsByVipHallFk(VipHallsEntity vipHallsByVipHallFk) {
        this.vipHallsByVipHallFk = vipHallsByVipHallFk;
    }
}
