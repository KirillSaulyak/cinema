$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-current-cinema-information",
                contentType: false,
                cache: false,
                processData: false,
                success: function (currentCinemasInformation) {
                    let currentCinema = JSON.parse(currentCinemasInformation.descriptionCurrentCinema);
                    let currentCinemasGallery = JSON.parse(currentCinemasInformation.descriptionCurrentCinemasGallery);

                    $("#currentCinemasTopBanner").attr("src", currentCinema.pathToMainImage)

                    $("#currentCinemasDescription").prepend(currentCinema.description);

                    for (let i = 0; i < currentCinemasGallery.length; i++) {
                        addImageToView(i, currentCinemasGallery[i].pathToImage)
                    }

                    function addImageToView(index, src) {
                        $("#currentCinemasGalleryCarouselIndicators").append(
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
                            $("#currentCinemasGalleryCarouselItem").append(
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