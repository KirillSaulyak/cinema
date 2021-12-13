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

                    $("#moviesName").prepend(movie.name);
                    $("#moviesPoster").attr("src", movie.pathToMainImage);

                }
            }
        )
    }
)