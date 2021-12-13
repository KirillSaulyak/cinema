package com.kinocms.cinema.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "main_pages_information", schema = "cino_cms", catalog = "")
public class MainPagesInformationEntity {
    private int idMainPageInformation;
    private String name;
    private String firstPhone;
    private String secondPhone;
    private boolean enabled;
    private Date creationDate;

    public MainPagesInformationEntity() {
        this.name = "Главная страница";
        this.enabled = false;
        this.creationDate = new Date(new java.util.Date().getTime());
    }

    public MainPagesInformationEntity(String firstPhone, String secondPhone, boolean enabled) {
        this.firstPhone = firstPhone;
        this.secondPhone = secondPhone;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_main_page_information", nullable = false)
    public int getIdMainPageInformation() {
        return idMainPageInformation;
    }

    public void setIdMainPageInformation(int idMainPageInformation) {
        this.idMainPageInformation = idMainPageInformation;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "first_phone", nullable = true, length = 45)
    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    @Basic
    @Column(name = "second_phone", nullable = true, length = -1)
    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainPagesInformationEntity)) return false;
        MainPagesInformationEntity that = (MainPagesInformationEntity) o;
        return idMainPageInformation == that.idMainPageInformation && enabled == that.enabled && name.equals(that.name) && Objects.equals(firstPhone, that.firstPhone) && Objects.equals(secondPhone, that.secondPhone) && creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMainPageInformation, name, firstPhone, secondPhone, enabled, creationDate);
    }
}
