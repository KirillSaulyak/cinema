$(document).ready(
    function () {
        $("#addPage").click(
            function (e) {
                e.preventDefault();
                window.location = "/admin/create-additional-page";
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/admin/list-pages",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (listEntries) {
                    for (let defaultEntry of listEntries.defaultEntries) {
                        addDefaultEntriesToTable(JSON.parse(defaultEntry))
                    }

                    function addDefaultEntriesToTable(entryToTable) {
                        $("#entryIntoTheListPagesTable").append(
                            $("<tr>").append(
                                $("<td>").text(entryToTable.name),
                                $("<td>").text(entryToTable.creationDate.toString()),

                                $("<td>").text(
                                    function () {
                                        if (entryToTable.enabled) {
                                            return "ВКЛ"
                                        } else {
                                            return "ВЫКЛ"
                                        }
                                    }),
                                $("<td>").append(
                                    $("<button>").attr({
                                        type: "button",
                                        class: "btn btn-primary mr-2",
                                        id: entryToTable.name,
                                    }).text("Изменить").click(
                                        function (e) {
                                            e.preventDefault();
                                            window.location = "/admin/edit-default-entry-" + this.id;
                                        }
                                    )
                                )
                            )
                        )
                    }

                    if ("additionalEntries" in listEntries) {
                        let additionalEntries = JSON.parse(listEntries.additionalEntries);
                        for (let i = 0; i < additionalEntries.length; i++) {
                            addAdditionalEntriesToTable(i, additionalEntries[i])
                        }

                        function addAdditionalEntriesToTable(id, entryToTable) {
                            $("#entryIntoTheListPagesTable").append(
                                $("<tr>").attr("id", "tableRow" + id).append(
                                    $("<td>").text(entryToTable.name),
                                    $("<td>").text(entryToTable.creationDate.toString()),
                                    $("<td>").text(
                                        function () {
                                            if (entryToTable.enabled) {
                                                return "ВКЛ"
                                            } else {
                                                return "ВЫКЛ"
                                            }
                                        }),
                                    $("<td>").append(
                                        $("<button>").attr({
                                            type: "button",
                                            class: "btn btn-primary mr-2",
                                            id: id
                                        }).text("Изменить").click(
                                            function (e) {
                                                e.preventDefault();
                                                window.location = "/admin/edit-additional-page?idAdditionalPage=" + additionalEntries[this.id].idAdditionalPage;
                                            }
                                        ),
                                        $("<button>").attr({
                                            type: "button",
                                            class: "btn btn-danger",
                                            name: id
                                        }).text("Удалить").click(
                                            function (e) {
                                                e.preventDefault();
                                                $("#tableRow" + this.name).detach();
                                                let formData = new FormData;
                                                formData.append("idAdditionalPage", additionalEntries[this.name].idAdditionalPage);
                                                additionalEntries.splice(this.name, 1);
                                                $.ajax(
                                                    {
                                                        type: 'DELETE',
                                                        url: "/admin/delete-additional-entry",
                                                        data: formData,
                                                        contentType: false,
                                                        cache: false,
                                                        processData: false,
                                                        success: function () {
                                                            alert("Удаление прошло успешно")
                                                            window.location = "/admin/list-pages"
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
            }
        )
    }
)