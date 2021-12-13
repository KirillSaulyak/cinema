$(document).ready(
    function () {
        $("#addUser").click(
            function (e) {
                e.preventDefault();
                window.location = "/admin/create-user";
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/admin/list-users",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (listUsers) {


                    let users = JSON.parse(listUsers);
                    for (let i = 0; i < users.length; i++) {
                        addAdditionalEntriesToTable(i, users[i])


                        function addAdditionalEntriesToTable(id, userToTable) {
                            $("#entryIntoTheListUsersTable").append(
                                $("<tr>").attr("id", "tableRow" + id).append(
                                    $("<td>").text(userToTable.idUser),
                                    $("<td>").text(userToTable.registrationDate),
                                    $("<td>").text(userToTable.dateOfBirth),
                                    $("<td>").text(userToTable.authorizationsByAuthorizationFk.eMail),
                                    $("<td>").text(userToTable.numberPhone),
                                    $("<td>").text(userToTable.firstName + " " + userToTable.secondName),
                                    $("<td>").text(userToTable.citiesByCityFk.city),
                                    $("<td>").append(
                                        $("<button>").attr({
                                            type: "button",
                                            class: "btn btn-primary mr-2",
                                            id: id
                                        }).text("Изменить").click(
                                            function (e) {
                                                e.preventDefault();
                                                window.location = "/admin/edit-user?idUser=" + users[this.id].idUser;
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
                                                formData.append("idUserDelete", users[this.name].idUser);
                                                users.splice(this.name, 1);

                                                $.ajax(
                                                    {
                                                        type: 'DELETE',
                                                        url: "/admin/delete-user",
                                                        data: formData,
                                                        contentType: false,
                                                        cache: false,
                                                        processData: false,
                                                        success: function () {
                                                            alert("Удаление прошло успешно")
                                                            window.location = "/admin/list-users"
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