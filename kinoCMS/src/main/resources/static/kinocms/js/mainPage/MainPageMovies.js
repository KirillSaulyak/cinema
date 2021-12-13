$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-all-movies-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (movies) {
                    let currentMovies = JSON.parse(movies.currentMovies)
                    for (let i = 0; i < currentMovies.length; i++) {
                        currentMovieToPage(i, currentMovies[i].name, currentMovies[i].pathToMainImage, true)
                    }

                    let futureMovies = JSON.parse(movies.futureMovies)
                    for (let i = 0; i < futureMovies.length; i++) {
                        futureMovieToPage(i, futureMovies[i].name, futureMovies[i].pathToMainImage, false)
                    }

                    function currentMovieToPage(id, name, src) {
                        $("#currentMovies").prepend(
                            $("<div>").attr("class", "col-2").prepend(
                                $("<img>").attr({
                                    class: "img-thumbnail",
                                    src: src,
                                    width: 200,
                                    alt: "Thumbnail image"
                                }),
                                $("<a>").attr("href", "/movie?idMovie=" + currentMovies[id].idMovie).prepend(
                                    $("<p>").attr("class", "text-center").text(name)
                                ),

                                $("<div>").attr("class", "row justify-content-center").prepend(
                                    $("<button>").attr({
                                        type: "button",
                                        class: "btn btn-success"
                                    }).click(
                                        function (e) {
                                            e.preventDefault();
                                            window.location = "/ticket-booking?idMovie=" + currentMovies[id].idMovie;
                                        }
                                    ).text("Купить")
                                )
                            )
                        )
                    }

                    function futureMovieToPage(id, name, src) {
                        $("#futureMovies").prepend(
                            $("<div>").attr("class", "col-2").prepend(
                                $("<img>").attr({
                                    class: "img-thumbnail",
                                    src: src,
                                    width: 200,
                                    alt: "Thumbnail image"
                                }),
                                $("<a>").attr("href", "/movie?idMovie=" + futureMovies[id].idMovie).prepend(
                                    $("<p>").attr("class", "text-center").text(name)
                                ),
                            )
                        )
                    }
                }
            }
        )
    }
)