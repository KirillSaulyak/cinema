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
                    let futureMovies = JSON.parse(movies.futureMovies)
                    for (let i = 0; i < futureMovies.length; i++) {
                        MovieToPage(i, futureMovies[i].name, futureMovies[i].pathToMainImage)
                    }

                    $("#userMoviesCurrent").click(
                        function (e) {
                            e.preventDefault();
                            window.location = "/poster";
                        }
                    )


                    function MovieToPage(id, name, src) {
                        $("#movies").prepend(
                            $("<div>").attr("class", "col-2").prepend(
                                $("<img>").attr({
                                    class: "img-thumbnail",
                                    src: src,
                                    width: 200,
                                    alt: "Thumbnail image"
                                }),
                                $("<a>").attr("href", "/movie?idMovie=" + futureMovies[id].idMovie).prepend(
                                    $("<p>").attr("class", "text-center").text(name)
                                )
                            )
                        )
                    }
                }
            }
        )
    }
)