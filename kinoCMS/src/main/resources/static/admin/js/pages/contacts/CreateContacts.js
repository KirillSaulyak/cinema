$(document).ready(
    function () {
        class ContactsInformation {
            nameCinema;
            address;
            coordinatesForTheMap;
            pathToLogo;

            constructor(nameCinema, address, coordinatesForTheMap, pathToLogo) {
                this._nameCinema = nameCinema;
                this._address = address;
                this._coordinatesForTheMap = coordinatesForTheMap;
                this._pathToLogo = pathToLogo;
            }

            get nameCinema() {
                return this._nameCinema;
            }

            set nameCinema(value) {
                this._nameCinema = value;
            }

            get address() {
                return this._address;
            }

            set address(value) {
                this._address = value;
            }

            get coordinatesForTheMap() {
                return this._coordinatesForTheMap;
            }

            set coordinatesForTheMap(value) {
                this._coordinatesForTheMap = value;
            }

            get pathToLogo() {
                return this._pathToLogo;
            }

            set pathToLogo(value) {
                this._pathToLogo = value;
            }
        }

        $("#uploadLogo").change(
            function () {
                let reader = new FileReader();
                reader.onload = () => $("#mainImage").attr("src", reader.result);
                reader.readAsDataURL(this.files[0]);
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

        $("#saveContact").click(
            function (e) {
                e.preventDefault();

                if (isValidFields()) {
                    let formData = new FormData;
                    let newContact = new ContactsInformation();

                    newContact.nameCinema = $("#contactsCinemaName")[0].value;
                    newContact.address = $("#contactsAddress")[0].value

                    newContact.coordinatesForTheMap = $("#contactsMapCoordinate")[0].value;

                    formData.append("contactsInformation", JSON.stringify(newContact));

                    formData.append("logo", $("#uploadLogo")[0].files[0]);

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/create-default-entry-Контакты",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function () {
                                alert("Сохранение прошло успешно")
                                window.location = "/admin/edit-default-entry-Контакты"
                            },
                            error: function () {
                                alert("Сохранить не удалось")
                            }
                        }
                    )
                }
            }
        )
    }
)