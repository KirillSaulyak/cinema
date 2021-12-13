$(document).ready(
    function () {
        class User {
            firstName;
            secondName;
            address;
            numberBankCard;
            numberPhone;
            dateOfBirth;

            constructor(firstName, secondName, address, numberBankCard, numberPhone, dateOfBirth) {
                this.firstName = firstName;
                this.secondName = secondName;
                this.address = address;
                this.numberBankCard = numberBankCard;
                this.numberPhone = numberPhone;
                this.dateOfBirth = dateOfBirth;
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
            eMail;
            password;

            constructor(eMail, password) {
                this.eMail = eMail;
                this.password = password;
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

        $.ajax(
            {
                type: 'POST',
                url: "/admin/user-default-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (defaultInformation) {
                    let usersLanguages = JSON.parse(defaultInformation.usersLanguages);
                    for (let language of usersLanguages) {
                        addLanguage(language.idLanguage, language.language)
                    }

                    let usersGenders = JSON.parse(defaultInformation.usersGenders);
                    for (let gender of usersGenders) {
                        addGender(gender.idGender, gender.gender)
                    }

                    let usersCities = JSON.parse(defaultInformation.usersCities);
                    for (let city of usersCities) {
                        addCity(city.idCity, city.city);
                    }

                    function addLanguage(id, languagesName) {
                        $("#userLanguages").append(
                            $("<div>").attr("class", "col").append(
                                $("<input>").attr({
                                    class: "form-check-input",
                                    type: "radio",
                                    name: "userLanguage",
                                    id: "userLanguage" + id,
                                    value: id,
                                    required: true
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

                    function addGender(id, gendersName) {
                        $("#userGenders").append(
                            $("<div>").attr("class", "col").append(
                                $("<input>").attr({
                                    class: "form-check-input",
                                    type: "radio",
                                    name: "userGender",
                                    id: "userGender" + id,
                                    value: id,
                                    required: true
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

                    function addCity(id, cityName) {
                        $("#listCities").append(
                            $("<option>").attr({
                                value: id
                            }).text(cityName)
                        )
                    }
                }
            }
        )

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
                        let user = new User($("#userFirstName")[0].value, $("#userSecondName")[0].value,
                                            $("#userAddress")[0].value,$("#userBankCard")[0].value,
                                            $("#userPhone")[0].value,$("#userDateOfBirth")[0].value);


                        formData.append("userInformation", JSON.stringify(user));
                        formData.append("idChosenLanguage", idChosenLanguage);
                        formData.append("idChosenGender", idChosenGender);
                        formData.append("idChosenCity", $("#listCities")[0].value);

                        let authorization = new Authorization($("#userEmail")[0].value,$("#userPassword")[0].value)

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
)