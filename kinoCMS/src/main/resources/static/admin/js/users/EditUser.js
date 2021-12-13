$(document).ready(
    function () {
        class User {
            idUser;
            firstName;
            secondName;
            address;
            numberBankCard;
            numberPhone;
            dateOfBirth;

            constructor(idUser,firstName, secondName, address, numberBankCard, numberPhone, dateOfBirth) {
                this.idUser = idUser;
                this.firstName = firstName;
                this.secondName = secondName;
                this.address = address;
                this.numberBankCard = numberBankCard;
                this.numberPhone = numberPhone;
                this.dateOfBirth = dateOfBirth;
            }

            get idUser() {
                return this.idUser;
            }

            set idUser(value) {
                this.idUser = value;
            }

            get firstName() {
                return this.firstName;
            }

            set firstName(value) {
                this.firstName = value;
            }

            get secondName() {
                return this.secondName;
            }

            set secondName(value) {
                this.secondName = value;
            }

            get address() {
                return this.address;
            }

            set address(value) {
                this.address = value;
            }

            get numberBankCard() {
                return this.numberBankCard;
            }

            set numberBankCard(value) {
                this.numberBankCard = value;
            }

            get numberPhone() {
                return this.numberPhone;
            }

            set numberPhone(value) {
                this.numberPhone = value;
            }

            get dateOfBirth() {
                return this.dateOfBirth;
            }

            set dateOfBirth(value) {
                this.dateOfBirth = value;
            }
        }

        class Authorization
        {
            idAuthorization
            eMail;
            password;

            constructor(idAuthorization,eMail, password) {
                this.idAuthorization = idAuthorization;
                this.eMail = eMail;
                this.password = password;
            }

            get idAuthorization() {
                return this.idAuthorization;
            }

            set idAuthorization(value) {
                this.idAuthorization = value;
            }

            get eMail() {
                return this.eMail;
            }

            set eMail(value) {
                this.eMail = value;
            }

            get password() {
                return this.password;
            }

            set password(value) {
                this.password = value;
            }
        }
        let idChosenLanguage;
        let idChosenGender;


        let formData = new FormData;
        formData.append("idUser", new URLSearchParams(window.location.search).get("idUser"))

        $.ajax(
            {
                type: 'POST',
                url: "/admin/get-user-information",
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (defaultAndUserInformations) {
                    let userInformations = JSON.parse(defaultAndUserInformations.usersInformation);
                    $("#userFirstName")[0].value = userInformations.firstName;
                    $("#userSecondName")[0].value = userInformations.secondName;
                    $("#userAddress")[0].value = userInformations.address;
                    $("#userBankCard")[0].value = userInformations.numberBankCard;
                    $("#userPhone")[0].value = userInformations.numberPhone;
                    $("#userDateOfBirth")[0].value = userInformations.dateOfBirth;
                    $("#userEmail")[0].value = userInformations.authorizationsByAuthorizationFk.eMail;
                    $("#userPassword")[0].value = userInformations.authorizationsByAuthorizationFk.password;
                    $("#userRepeatPassword")[0].value = userInformations.authorizationsByAuthorizationFk.password;

                    let usersLanguages = JSON.parse(defaultAndUserInformations.defaultUsersLanguages);
                    for (let language of usersLanguages) {
                        addLanguage(language.idLanguage, language.language, userInformations.languagesByLanguageFk.idLanguage)
                    }

                    let usersGenders = JSON.parse(defaultAndUserInformations.defaultUsersGenders);
                    for (let gender of usersGenders) {
                        addGender(gender.idGender, gender.gender, userInformations.gendersByGenderFk.idGender)
                    }

                    let usersCities = JSON.parse(defaultAndUserInformations.defaultUsersCities);
                    for (let city of usersCities) {
                        addCity(city.idCity, city.city, userInformations.citiesByCityFk.idCity);
                    }

                    function addLanguage(id, languagesName, chosenId) {
                        $("#userLanguages").append(
                            $("<div>").attr("class", "col").append(
                                $("<input>").attr({
                                    class: "form-check-input",
                                    type: "radio",
                                    name: "userLanguage",
                                    id: "userLanguage" + id,
                                    value: id,
                                    required: true,
                                    checked:
                                        function () {
                                            if (chosenId === id) {
                                                idChosenLanguage = chosenId;
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        }
                                }).change(
                                    function () {
                                        idChosenLanguage = this.value;
                                    }
                                ),
                                $(" <label>").attr({
                                    class: "form-check-label",
                                    for: "userLanguage" + id
                                }).text(languagesName)
                            ),
                        )
                    }

                    function addGender(id, gendersName, chosenId) {
                        $("#userGenders").append(
                            $("<div>").attr("class", "col").append(
                                $("<input>").attr({
                                    class: "form-check-input",
                                    type: "radio",
                                    name: "userGender",
                                    id: "userGender" + id,
                                    value: id,
                                    required: true,
                                    checked:
                                        function () {
                                            if (chosenId === id) {
                                                idChosenGender = chosenId;
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        }
                                }).change(
                                    function () {
                                        idChosenGender = this.value;
                                    }
                                ),
                                $(" <label>").attr({
                                    class: "form-check-label",
                                    for: "userGender" + id
                                }).text(gendersName)
                            ),
                        )
                    }

                    function addCity(id, cityName, chosenId) {
                        $("#listCities").append(
                            $("<option>").attr({
                                value: id,
                                selected:
                                    function () {
                                        if (chosenId === id) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                            }).text(cityName)
                        )
                    }

                    function isValidFields() {
                        let requiredFields = $(":required");
                        for (let field of requiredFields) {
                            if (field.validity.valueMissing || field.validity.typeMismatch) {
                                $("#container")[0].classList.add("was-validated");
                                return false;
                            }
                        }
                        return true;
                    }

                    $("#saveUser").click(
                        function (e) {
                            e.preventDefault();
                            if (isValidFields()) {
                                if ($("#userPassword")[0].value !== $("#userRepeatPassword")[0].value) {
                                    alert("Пароли не совпадают");
                                } else {
                                    let formData = new FormData;
                                    let user = new User(userInformations.idUser,$("#userFirstName")[0].value, $("#userSecondName")[0].value,
                                        $("#userAddress")[0].value, $("#userBankCard")[0].value,
                                        $("#userPhone")[0].value, $("#userDateOfBirth")[0].value);


                                    formData.append("userInformation", JSON.stringify(user));
                                    formData.append("idChosenLanguage", idChosenLanguage);
                                    formData.append("idChosenGender", idChosenGender);
                                    formData.append("idChosenCity", $("#listCities")[0].value);

                                    let authorization = new Authorization(userInformations.authorizationsByAuthorizationFk.idAuthorization,
                                        $("#userEmail")[0].value, $("#userPassword")[0].value)

                                    formData.append("userAuthorization", JSON.stringify(authorization));
                                    $.ajax(
                                        {
                                            type: 'POST',
                                            url: "/admin/user-save",
                                            data: formData,
                                            contentType: false,
                                            cache: false,
                                            processData: false,
                                            success: function () {
                                                alert("Сохранение прошло успешно")
                                                window.location = "/admin/list-users"
                                            },
                                            error: function () {
                                                alert("Сохранить не удалось")
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    )

                }
            }
        )

    }
)