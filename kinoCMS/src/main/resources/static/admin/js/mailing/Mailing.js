$(document).ready(
    function () {
        showLoadedFiles();
        function isValidFields() {
            let requiredFields = $(":required");
            for (let field of requiredFields) {
                if (field.validity.valueMissing || field.validity.typeMismatch) {
                    $("#validateUploadFile")[0].classList.add("was-validated");
                    return false;
                }
            }
            return true;
        }


        $("[name ='mailingChoseUsers']").click(
            function (e) {
                e.preventDefault();
                window.location = "/admin/mailing-user-list"
            }
        )

        $("#mailingUploadFile").click(
            function (e) {
                e.preventDefault();
                if (isValidFields()) {
                    let formData = new FormData;
                    formData.append("fileForMailing", $("#fileForMailing")[0].files[0]);
                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/mailing-save-file",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function () {
                                alert("Сохранение прошло успешно")
                                $("#mailingInformationFileUploaded").text("Загружен файл: " + $("#fileForMailing")[0].files[0].name)
                                showLoadedFiles();
                            },
                            error: function () {
                                alert("Сохранить не удалось")
                            }
                        }
                    )
                }
            }
        )

        function showLoadedFiles() {
            $.ajax(
                {
                    type: 'POST',
                    url: "/admin/show-loaded-files",
                    contentType: JSON,
                    cache: false,
                    processData: false,
                    success: function (listMailingsFiles) {
                        listMailingsFiles = JSON.parse(listMailingsFiles);
                        $("#entryIntoTheListMailingsFileTable").children().detach();
                        for (let i = 0; i < listMailingsFiles.length; i++) {
                            addMailingsFileToTable(i, listMailingsFiles[i])
                        }

                        function addMailingsFileToTable(id, mailingsFileToTable) {
                            $("#entryIntoTheListMailingsFileTable").append(
                                $("<tr>").attr("id", "tableRow" + id).append(
                                    $("<td>").append(
                                        $("<div>").attr("class", "custom-control custom-radio").prepend(
                                            $("<input>").attr({
                                                type: "radio",
                                                id: "customRadio" + id,
                                                name: "customRadio",
                                                class: "custom-control-input"
                                            }).click(
                                                function () {
                                                    $("#mailingUsingTemplate").text(
                                                        listMailingsFiles[(this.id.replace(/\D+/g, ""))].originalName
                                                    );
                                                }
                                            ),
                                            $("<label>").attr({
                                                class: "custom-control-label",
                                                for: "customRadio" + id
                                            })
                                        )
                                    ),
                                    $("<td>").text(mailingsFileToTable.originalName),

                                    $("<td>").append(
                                        $("<button>").attr({
                                            type: "button",
                                            class: "btn btn-danger",
                                            name: id
                                        }).text("Удалить").click(
                                            function (e) {
                                                e.preventDefault();
                                                $("#tableRow" + this.name).detach();
                                                let formData = new FormData;
                                                formData.append("idMailing", listMailingsFiles[this.name].idMailing);
                                                listMailingsFiles.splice(this.name, 1);
                                                $.ajax(
                                                    {
                                                        type: 'DELETE',
                                                        url: "/admin/delete-mailing-file",
                                                        data: formData,
                                                        contentType: false,
                                                        cache: false,
                                                        processData: false,
                                                        success: function () {
                                                            alert("Удаление прошло успешно")
                                                            window.location = "/admin/mailing"
                                                        },
                                                        error: function () {
                                                            alert("удалить не удалось")
                                                        }
                                                    }
                                                )
                                            }
                                        )
                                    )
                                )
                            )
                        }
                    }

                }
            )
        }
    }
)