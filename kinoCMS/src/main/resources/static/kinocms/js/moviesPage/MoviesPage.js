$(document).ready(
    function () {
        let formData = new FormData;
        formData.append("idMovie", (new URLSearchParams(window.location.search).get("idMovie")))

        $.ajax(
            {
                type: 'POST',
                url: "/send-chosen-movie-information",
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (moviesInformation) {
                    let movie = JSON.parse(moviesInformation.movie);
                    let moviesTypes = JSON.parse(moviesInformation.moviesTypes);
                    let moviesGallery = JSON.parse(moviesInformation.moviesGallery);

                    $("#placeBuyTicketButton").prepend(
                        $("<button>").attr({
                            type: "button",
                            class: "btn btn-success btn-lg",
                            id: movie.idMovie
                        }).click(
                            function (e) {
                                e.preventDefault();
                                window.location = "/ticket-booking?idMovie=" + this.id;
                            }
                        ).text("Купить билет")
                    )

                    $("#moviesName").prepend(movie.name);
                    for (let type of moviesTypes)
                        $("#moviesTypes").append(
                            $("<h1>").attr("class", "mr-3").prepend(type.type)
                        )
                    $("#moviesDescription").prepend(movie.description);
                    $("#moviesPoster").attr("src", movie.pathToMainImage);
                    $("#moviesTrailer").attr("src", movie.trailerLink)

                    for (let i = 0; i < moviesGallery.length; i++) {
                        addImageToView(i, moviesGallery[i].pathToImage,
                            moviesGallery[i].url)
                    }

                    function addImageToView(index, src) {
                        $("#moviesCarouselIndicators").append(
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
                            $("#moviesCarouselItem").append(
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
                                        class: "d-block ",
                                        style: "width: 100%;height: 400px",
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