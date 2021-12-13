$(document).ready(
    function () {

        $.ajax(
            {
                type: 'POST',
                url: "/send-all-cinemas-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (cinemasInformation) {
                    let cinemas = JSON.parse(cinemasInformation.cinemas)
                    for (let i = 0; i < cinemas.length; i++) {
                        cinemaToPage(i, cinemas[i].name, cinemas[i].pathToLogo)
                    }

                    function cinemaToPage(id, name, src) {
                        $("#cinemas").prepend(
                            $("<div>").attr("class", "mx-3").prepend(
                                $("<img>").attr({
                                    class: "img-thumbnail",
                                    src: src,
                                    width: 200,
                                    id: id,
                                    alt: "Thumbnail image"
                                }).click(
                                    function (e) {
                                        e.preventDefault();
                                        window.location = "/cinema?idCinema=" + cinemas[this.id].idCinema;
                                    }),
                                $("<p>").attr("class", "text-center").text(name)
                            )
                        )
                    }
                }
            }
        )
    }
)