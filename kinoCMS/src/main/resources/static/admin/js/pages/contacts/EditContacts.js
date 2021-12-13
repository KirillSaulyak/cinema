$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/admin/send-default-entry-Контакты",
                contentType: false,
                cache: false,
                processData: false,
                success: function (isEnabledContacts) {
                    $("#enabledContacts")[0].checked = JSON.parse(isEnabledContacts);
                }
            }
        )

        $("#addContact").click(
            function (e) {
                e.preventDefault();
                window.location = "/admin/create-default-entry-Контакты"
            }
        )

        $("#enabledContacts").change(
            function () {
                let formData = new FormData;
                formData.append("enabled",this.checked);
                $.ajax(
                    {
                        type: 'POST',
                        url: "/admin/save-default-entry-Контакты",
                        data: formData,
                        contentType: false,
                        cache: false,
                        processData: false,
                        success: function () {
                        },
                        error: function () {
                            alert("Сохранить не удалось")
                        }
                    }
                )
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/admin/edit-default-entry-contacts-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (contactsInformation) {


                    contactsInformation = JSON.parse(contactsInformation);
                    for (let i = 0; i < contactsInformation.length; i++) {
                        createCardContactsInformation(i, contactsInformation[i].nameCinema,
                            contactsInformation[i].address, contactsInformation[i].coordinatesForTheMap,
                            contactsInformation[i].pathToLogo);
                    }

                    function isValidFields(id) {
                        let requiredFields = $(":required");
                        for (let field of requiredFields) {
                            if (field.validity.valueMissing || field.validity.typeMismatch) {
                                $("#card" + id)[0].classList.add("was-validated");
                                return false;
                            }
                        }
                        return true;
                    }

                    function createCardContactsInformation(id, nameCinema, address, coordinatesForTheMap, srcLogo) {
                        $("#contactsInformationCard").append(
                            $("<div>").attr({
                                class: "card my-3",
                                id: "card" + id
                            }).prepend(
                                $("<div>").attr("class", "card-body").prepend(
                                    $("<div>").attr("class", "row").prepend(
                                        $("<div>").attr("class", "col").prepend(
                                            $("<label>").attr("for", "contactsCinemaName").text("Название кинотеатра: ")
                                        )
                                    ),
                                    $("<div>").attr("class", "row").prepend(
                                        $("<div>").attr("class", "col").prepend(
                                            $("<input>").attr({
                                                class: "form-control form-control-sm w-50",
                                                type: "text",
                                                id: "contactsCinemaName" + id,
                                                name: "contactsCinemaName",
                                                placeholder: "Текст",
                                                value: nameCinema,
                                                required: true
                                            })
                                        )
                                    ),

                                    $("<div>").attr("class", "row").prepend(
                                        $("<div>").attr("class", "col").prepend(
                                            $("<label>").attr("for", "contactsAddress").text("Адресс: ")
                                        )
                                    ),

                                    $("<div>").attr("class", "row").prepend(
                                        $("<div>").attr("class", "col").prepend(
                                            $("<textarea>").attr({
                                                    class: "form-control",
                                                    id: "contactsAddress" + id,
                                                    name: "contactsAddress",
                                                    rows: "10",
                                                    placeholder: "Текст",
                                                    required: true
                                                }
                                            ).text(address)
                                        )
                                    ),

                                    $("<div>").attr("class", "row").prepend(
                                        $("<div>").attr("class", "col").prepend(
                                            $("<label>").attr("for", "contactsMapCoordinate").text("Координаты для карты: ")
                                        )
                                    ),

                                    $("<div>").attr("class", "row").prepend(
                                        $("<div>").attr("class", "col").prepend(
                                            $("<input>").attr({
                                                class: "form-control form-control-sm w-50",
                                                type: "text",
                                                id: "contactsMapCoordinate" + id,
                                                name: "contactsMapCoordinate",
                                                placeholder: "Текст",
                                                value: coordinatesForTheMap,
                                                required: true
                                            })
                                        )
                                    ),

                                    $("<div>").attr("class", "row mt-3").prepend(
                                        $("<div>").attr("class", "col-lg-2").prepend(
                                            $("<label>").attr({
                                                for: "mainImage",
                                                class: "form-label"
                                            }).text("Главная картинка: ")
                                        ),
                                        $("<div>").attr("class", "col").prepend(
                                            $("<img>").attr({
                                                class: "img-thumbnail",
                                                src: srcLogo,
                                                id: "mainImage" + id,
                                                alt: "default or upload img",
                                                width: "80"
                                            })
                                        )
                                    ),

                                    $("<div>").attr("class", "row mt-3 ml-1").prepend(
                                        $("<div>").attr("class", "col-11").prepend(
                                            $("<input>").attr({
                                                type: "file",
                                                class: "custom-file-input",
                                                name: "uploadLogo",
                                                id: "uploadLogo" + id,
                                                accept: "image/*"
                                            }).change(
                                                function () {
                                                    let reader = new FileReader();
                                                    reader.onload = () => $("#mainImage" + (this.id.match(/\d/g))).attr("src", reader.result);
                                                    reader.readAsDataURL(this.files[0]);
                                                }
                                            ),
                                            $("<label>").attr({
                                                for: "uploadLogo",
                                                class: "custom-file-label"
                                            }).text("Выберите изображение")
                                        ),

                                        $("<div>").attr("class", "row my-3").prepend(
                                            $("<div>").attr("class", "col d-flex justify-content-between").prepend(
                                                $("<button>").attr({
                                                    type: "button",
                                                    class: "btn btn-success",
                                                    id: "saveContact" + id
                                                }).text("Сохранить").click(
                                                    function (e) {
                                                        e.preventDefault()
                                                        if (isValidFields(this.id.match(/\d/g))) {
                                                            let formData = new FormData;

                                                            contactsInformation[(this.id.match(/\d/g))].nameCinema = $("#contactsCinemaName" + (this.id.match(/\d/g)))[0].value;
                                                            contactsInformation[(this.id.match(/\d/g))].address = $("#contactsAddress" + (this.id.match(/\d/g)))[0].value

                                                            contactsInformation[(this.id.match(/\d/g))].coordinatesForTheMap = $("#contactsMapCoordinate" + (this.id.match(/\d/g)))[0].value;

                                                            formData.append("contactsInformation", JSON.stringify(contactsInformation[(this.id.match(/\d/g))]));

                                                            formData.append("logo", $("#uploadLogo" + (this.id.match(/\d/g)))[0].files[0]);

                                                            $.ajax(
                                                                {
                                                                    type: 'POST',
                                                                    url: "/admin/save-default-entry-contacts-information",
                                                                    data: formData,
                                                                    contentType: false,
                                                                    cache: false,
                                                                    processData: false,
                                                                    success: function () {
                                                                        alert("Сохранение прошло успешно")
                                                                    },
                                                                    error: function () {
                                                                        alert("Сохранить не удалось")
                                                                    }
                                                                }
                                                            )
                                                        }
                                                    }
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    }

                }
            }
        )
    }
)