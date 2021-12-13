$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/admin/edit-default-entry-Главная страница",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (mainPagesEntry) {
                    mainPagesEntry = JSON.parse(mainPagesEntry);
                    if (mainPagesEntry.firstPhone !== undefined) {
                        $("#fistNumberPhone")[0].value = mainPagesEntry.firstPhone;
                    }
                    if (mainPagesEntry.secondPhone !== undefined) {
                        $("#secondNumberPhone")[0].value = mainPagesEntry.secondPhone;
                    }
                    $("#enabledMainPage")[0].checked = mainPagesEntry.enabled;

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

                    $("#saveMainPage").click(
                        function (e) {
                            e.preventDefault();
                            if (isValidFields()) {
                                let formData = new FormData;
                                mainPagesEntry.firstPhone = $("#fistNumberPhone")[0].value
                                mainPagesEntry.secondPhone = $("#secondNumberPhone")[0].value
                                mainPagesEntry.enabled = $("#enabledMainPage")[0].checked

                                formData.append("mainPagesInformation", JSON.stringify(mainPagesEntry));


                                $.ajax(
                                    {
                                        type: 'POST',
                                        url: "/admin/save-default-entry-Главная страница",
                                        data: formData,
                                        contentType: false,
                                        cache: false,
                                        processData: false,
                                        success: function () {
                                            alert("Сохранение прошло успешно")
                                            window.location = "/admin/list-pages"
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
            }
        )
    }
)