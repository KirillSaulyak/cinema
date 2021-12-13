$(document).ready(
    function () {
        $("#addNews").click(
            function (e){
                e.preventDefault();
                window.location="/admin/create-news";
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/admin/news",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (listNews) {
                   let allNews = JSON.parse(listNews);
                   for (let i = 0; i < allNews.length; i++){
                       addNewsToTable(i, allNews[i])
                   }
                    function addNewsToTable(id, newsToTable) {
                        $("#entryIntoTheNewsTable").append(
                            $("<tr>").attr("id", "tableRow" + id).append(
                                $("<td>").text(newsToTable.name),
                                $("<td>").text(newsToTable.creationDate.toString()),
                                $("<td>").text(
                                    function () {
                                        if (newsToTable.enabled) {
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
                                            window.location="/admin/edit-news?idNews="+allNews[this.id].idNews
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
                                            formData.append("idNews", allNews[this.name].idNews);
                                            allNews.splice(this.name, 1);
                                            $.ajax(
                                                {
                                                    type: 'DELETE',
                                                    url: "/admin/delete-news",
                                                    data: formData,
                                                    contentType: false,
                                                    cache: false,
                                                    processData: false,
                                                    success: function () {
                                                        alert("Удаление прошло успешно")
                                                        window.location = "/admin/news"
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