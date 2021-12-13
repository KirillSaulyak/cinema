package com.kinocms.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts_information", schema = "cino_cms", catalog = "")
public class ContactsInformationEntity {
    private int idContact;
    private String nameCinema;
    private String address;
    private String coordinatesForTheMap;
    private String pathToLogo;
    private ContactsEntity contactsByContactFk;

    public ContactsInformationEntity() {
    }

    public ContactsInformationEntity(String nameCinema, String address, String coordinatesForTheMap, String pathToLogo,
                                     ContactsEntity contactsByContactFk) {
        this.nameCinema = nameCinema;
        this.address = address;
        this.coordinatesForTheMap = coordinatesForTheMap;
        this.pathToLogo = pathToLogo;
        this.contactsByContactFk = contactsByContactFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact", nullable = false)
    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    @Basic
    @Column(name = "name_cinema", nullable = false, length = 45)
    public String getNameCinema() {
        return nameCinema;
    }

    public void setNameCinema(String nameCinema) {
        this.nameCinema = nameCinema;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "coordinates_for_the_map", nullable = false, length = 100)
    public String getCoordinatesForTheMap() {
        return coordinatesForTheMap;
    }

    public void setCoordinatesForTheMap(String coordinatesForTheMap) {
        this.coordinatesForTheMap = coordinatesForTheMap;
    }

    @Basic
    @Column(name = "path_to_logo", nullable = false, length = 255)
    public String getPathToLogo() {
        return pathToLogo;
    }

    public void setPathToLogo(String pathToLogo) {
        this.pathToLogo = pathToLogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsInformationEntity that = (ContactsInformationEntity) o;

        if (idContact != that.idContact) return false;
        if (nameCinema != null ? !nameCinema.equals(that.nameCinema) : that.nameCinema != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (coordinatesForTheMap != null ? !coordinatesForTheMap.equals(that.coordinatesForTheMap) : that.coordinatesForTheMap != null)
            return false;
        if (pathToLogo != null ? !pathToLogo.equals(that.pathToLogo) : that.pathToLogo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContact;
        result = 31 * result + (nameCinema != null ? nameCinema.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (coordinatesForTheMap != null ? coordinatesForTheMap.hashCode() : 0);
        result = 31 * result + (pathToLogo != null ? pathToLogo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "contact_fk", referencedColumnName = "id_contact", nullable = false)
    public ContactsEntity getContactsByContactFk() {
        return contactsByContactFk;
    }

    public void setContactsByContactFk(ContactsEntity contactsByContactFk) {
        this.contactsByContactFk = contactsByContactFk;
    }
}
