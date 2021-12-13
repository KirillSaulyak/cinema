package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "childrens_rooms_galleries", schema = "cino_cms", catalog = "")
public class ChildrensRoomsGalleriesEntity {
    private int idChildrensRoomsGallery;
    private String pathToImage;
    private ChildrensRoomsEntity childrensRoomsByChildrensRoomFk;

    public ChildrensRoomsGalleriesEntity() {
    }

    public ChildrensRoomsGalleriesEntity(String pathToImage, ChildrensRoomsEntity childrensRoomsByChildrensRoomFk) {
        this.pathToImage = pathToImage;
        this.childrensRoomsByChildrensRoomFk = childrensRoomsByChildrensRoomFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_childrens_rooms_gallery", nullable = false)
    public int getIdChildrensRoomsGallery() {
        return idChildrensRoomsGallery;
    }

    public void setIdChildrensRoomsGallery(int idChildrensRoomsGallery) {
        this.idChildrensRoomsGallery = idChildrensRoomsGallery;
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

        ChildrensRoomsGalleriesEntity that = (ChildrensRoomsGalleriesEntity) o;

        if (idChildrensRoomsGallery != that.idChildrensRoomsGallery) return false;
        if (pathToImage != null ? !pathToImage.equals(that.pathToImage) : that.pathToImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idChildrensRoomsGallery;
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "childrens_room_fk", referencedColumnName = "id_childrens_room", nullable = false)
    public ChildrensRoomsEntity getChildrensRoomsByChildrensRoomFk() {
        return childrensRoomsByChildrensRoomFk;
    }

    public void setChildrensRoomsByChildrensRoomFk(ChildrensRoomsEntity childrensRoomsByChildrensRoomFk) {
        this.childrensRoomsByChildrensRoomFk = childrensRoomsByChildrensRoomFk;
    }
}
