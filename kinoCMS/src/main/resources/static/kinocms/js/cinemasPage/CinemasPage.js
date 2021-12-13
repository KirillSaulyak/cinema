$(document).ready(
    function () {
        let formData = new FormData;
        formData.append("idCinema", (new URLSearchParams(window.location.search).get("idCinema")))

        $.ajax(
            {
                type: 'POST',
                url: "/send-chosen-cinema-information",
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (cinemasInformation) {
                    let cinema = JSON.parse(cinemasInformation.cinema);
                    let cinemasGallery = JSON.parse(cinemasInformation.cinemasGallery);
                    let cinemasHalls = JSON.parse(cinemasInformation.cinemasHalls);
                    $("[name ='schedule']").click(
                        function (e) {
                            e.preventDefault();
                            window.location = "/schedule";
                        }
                    )
                    $("#cinemasTopBanner").attr("src", cinema.pathToTopBanner)
                    $("#cinemasLogo").attr("src", cinema.pathToLogo)

                    $("#cinemasName").prepend(cinema.name);

                    $("#cinemasDescription").prepend(cinema.description);
                    $("#cinemasConditions").prepend(cinema.conditions);

                    $("#placeCountHalls").append(cinemasHalls.length);
                    for (let i = 0; i < cinemasHalls.length; i++) {
                        addHallToTable(i, cinemasHalls[i])
                    }

                    for (let i = 0; i < cinemasGallery.length; i++) {
                        addImageToView(i, cinemasGallery[i].pathToImage)
                    }

                    function addHallToTable(id, hall) {
                        $("#placeCinemasHalls").append(
                            $("<tr>").attr("id", "tableRow" + id).append(
                                $("<td>").prepend(
                                    $("<a>").attr("href", "/hall?idHall=" + hall.idHall).prepend(hall.name)
                                )
                            )
                        )
                    }

                    function addImageToView(index, src) {
                        $("#cinemasGalleryCarouselIndicators").append(
                            $("<li>").attr({
                                "data-target": "#carouselExampleIndicators",
                                "data-slide-to": index,
                                class:
                                    function () {
                                        if (index === 0) {
                                            return "active"
                                        } else {
                                            return " "
                                        }
                                    }
                            })
                        ),
                            $("#cinemasGalleryCarouselItem").append(
                                $("<div>").attr({
                                    class:
                                        function () {
                                            if (index === 0) {
                                                return " carousel-item active"
                                            } else {
                                                return "carousel-item"
                                            }
                                        },

                                }).prepend(
                                    $("<img>").attr({
                                        class: "d-block",
                                        style: "width: 500px;height: 200px",
                                        src: src,
                                        alt: "First slide"
                                    })
                                )
                            )
                    }

                }
            }
        )
    }
)