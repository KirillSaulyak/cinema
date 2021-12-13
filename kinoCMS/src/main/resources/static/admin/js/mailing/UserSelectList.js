$(document).ready(
    function () {
$("#userSelectListchoose").click(
    function (e)
    {
        e.preventDefault();
        window.location = "/admin/mailing";
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
                                    $("<td>").append(
                                        $("<div>").attr("class", "custom-control custom-checkbox").prepend(
                                            $("<input>").attr({
                                                type: "checkbox",
                                                id: "customCheckbox" + id,
                                                name: "customCheckbox",
                                                class: "custom-control-input"
                                            }),
                                            $("<label>").attr({
                                                class: "custom-control-label",
                                                for: "customCheckbox" + id
                                            }).text(" ")
                                        )
                                    ),
                                    $("<td>").text(userToTable.idUser),
                                    $("<td>").text(userToTable.registrationDate),
                                    $("<td>").text(userToTable.dateOfBirth),
                                    $("<td>").text(userToTable.authorizationsByAuthorizationFk.eMail),
                                    $("<td>").text(userToTable.numberPhone),
                                    $("<td>").text(userToTable.firstName + " " + userToTable.secondName),
                                    $("<td>").text(userToTable.citiesByCityFk.city),
                                )
                            )
                        }
                    }
                }
            }
        )
    }
)