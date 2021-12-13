$(document).ready(
    function () {
        $("#addDeal").click(
            function (e){
                e.preventDefault();
                window.location="/admin/create-deal";
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/admin/deals",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (listDeals) {
                    let allDeals = JSON.parse(listDeals);
                    for (let i = 0; i < allDeals.length; i++){
                        addDealsToTable(i, allDeals[i])
                    }
                    function addDealsToTable(id, dealsToTable) {
                        $("#entryIntoTheDealsTable").append(
                            $("<tr>").attr("id", "tableRow" + id).append(
                                $("<td>").text(dealsToTable.name),
                                $("<td>").text(dealsToTable.creationDate.toString()),
                                $("<td>").text(
                                    function () {
                                        if (dealsToTable.enabled) {
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
                                            window.location="/admin/edit-deal?idDeal="+allDeals[this.id].idDeal
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
                                            formData.append("idDeal", allDeals[this.name].idDeal);
                                            allDeals.splice(this.name, 1);
                                            $.ajax(
                                                {
                                                    type: 'DELETE',
                                                    url: "/admin/delete-deal",
                                                    data: formData,
                                                    contentType: false,
                                                    cache: false,
                                                    processData: false,
                                                    success: function () {
                                                        alert("Удаление прошло успешно")
                                                        window.location = "/admin/deals"
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
)