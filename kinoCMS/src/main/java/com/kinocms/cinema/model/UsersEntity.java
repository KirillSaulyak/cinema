package com.kinocms.cinema.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users", schema = "cino_cms", catalog = "")
public class UsersEntity {
    private int idUser;
    private String firstName;
    private String secondName;
    private String address;
    private int numberBankCard;
    private String numberPhone;
    private Date dateOfBirth;
    private Date registrationDate;
    private LanguagesEntity languagesByLanguageFk;
    private GendersEntity gendersByGenderFk;
    private CitiesEntity citiesByCityFk;
    private AuthorizationsEntity authorizationsByAuthorizationFk;

    public UsersEntity() {
    }

    public UsersEntity(String firstName, String secondName, String address, int numberBankCard, String numberPhone,
                       Date dateOfBirth, Date registrationDate, LanguagesEntity languagesByLanguageFk, GendersEntity gendersByGenderFk, CitiesEntity citiesByCityFk, AuthorizationsEntity authorizationsByAuthorizationFk) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.numberBankCard = numberBankCard;
        this.numberPhone = numberPhone;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.languagesByLanguageFk = languagesByLanguageFk;
        this.gendersByGenderFk = gendersByGenderFk;
        this.citiesByCityFk = citiesByCityFk;
        this.authorizationsByAuthorizationFk = authorizationsByAuthorizationFk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "second_name", nullable = false, length = 45)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
    @Column(name = "number_bank_card", nullable = false)
    public int getNumberBankCard() {
        return numberBankCard;
    }

    public void setNumberBankCard(int numberBankCard) {
        this.numberBankCard = numberBankCard;
    }

    @Basic
    @Column(name = "number_phone", nullable = false, length = 45)
    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Basic
    @Column(name = "date_of_birth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "registration_date", nullable = false)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idUser != that.idUser) return false;
        if (numberBankCard != that.numberBankCard) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (numberPhone != null ? !numberPhone.equals(that.numberPhone) : that.numberPhone != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (registrationDate != null ? !registrationDate.equals(that.registrationDate) : that.registrationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + numberBankCard;
        result = 31 * result + (numberPhone != null ? numberPhone.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "language_fk", referencedColumnName = "id_language", nullable = false)
    public LanguagesEntity getLanguagesByLanguageFk() {
        return languagesByLanguageFk;
    }

    public void setLanguagesByLanguageFk(LanguagesEntity languagesByLanguageFk) {
        this.languagesByLanguageFk = languagesByLanguageFk;
    }

    @ManyToOne
    @JoinColumn(name = "gender_fk", referencedColumnName = "id_gender", nullable = false)
    public GendersEntity getGendersByGenderFk() {
        return gendersByGenderFk;
    }

    public void setGendersByGenderFk(GendersEntity gendersByGenderFk) {
        this.gendersByGenderFk = gendersByGenderFk;
    }

    @ManyToOne
    @JoinColumn(name = "city_fk", referencedColumnName = "id_city", nullable = false)
    public CitiesEntity getCitiesByCityFk() {
        return citiesByCityFk;
    }

    public void setCitiesByCityFk(CitiesEntity citiesByCityFk) {
        this.citiesByCityFk = citiesByCityFk;
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
