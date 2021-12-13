$(document).ready(
    function () {
        if (cinemas.length > 0) {

            cinemas = JSON.parse(cinemas.replace(/&quot;/g, '"'));
            let countCinemas = 0;
            let mapCinemas = new Map;

            for (let cinema of cinemas) {
                mapCinemas.set(countCinemas.toString(), cinema)
                $("#linkToCreateCinema").before(
                    $("<div>").attr(
                        {
                            class: "my-2",
                            id: "cinema" + countCinemas
                        }
                    ).prepend(
                        $("<div>").attr("class", "col justify-content-center").prepend(
                            $("<img>").attr(
                                {
                                    class: "img-thumbnail",
                                    src: cinema.pathToLogo,
                                    id: cinema.idCinema,
                                    width: "100"
                                }
                            ).click(
                                function (e) {
                                    e.preventDefault();
                                    window.location = "/admin/edit-cinema?idCinema=" + this.id;
                                }
                            ),
                            $('<img>').attr({
                                class: "img-fluid align-top mr-2",
                                src: "images/close.svg",
                                alt: "close",
                                id: countCinemas
                            }).click(function (e) {
                                e.preventDefault();
                                deleteCinema(this.id)
                                $("#cinema" + this.id).detach();
                            }),
                            $("<div>").attr("class", "row").prepend(
                                $("<div>").attr("class", "col").prepend(
                                    $('<p>').attr("class", "text-center").text(cinema.name)
                                )
                            )
                        )

                    )
                )
                countCinemas++;
            }

            function deleteCinema(id) {
                let formData = new FormData();
                formData.append("cinemasId", mapCinemas.get(id).idCinema);
                $.ajax(
                    {
                        type: 'DELETE',
                        url: "/admin/delete-cinema",
                        data: formData,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function () {
                            window.location = "/admin/cinemas"
                        },
                        error: function () {
                            alert("Удалить не удалось")
                        }
                    }
                )
            }
        }
    }
)
