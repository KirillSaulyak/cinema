$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-advertisement-information",
                contentType: false,
                cache: false,
                processData: false,
                success: function (advertisementsInformation) {
                    let advertisement = JSON.parse(advertisementsInformation.advertisement);
                    let advertisementsGallery = JSON.parse(advertisementsInformation.advertisementsGallery);
                    let quantityPureImagesOnPage = 3;
                    $("#advertisementsName").prepend(advertisement.name);

                    $("#advertisementsDescription").prepend(advertisement.description);
                    $("#advertisementsPoster").attr("src", advertisement.pathToMainImage);
                    $("#advertisementsVideo").attr("src", advertisement.linkToVideo)

                    for (let i = 0; i < advertisementsGallery.length; i++) {
                        if (i < quantityPureImagesOnPage) {
                            addImageToPage(i, advertisementsGallery[i].pathToImage)
                        }
                        addImageToCarousel(i, advertisementsGallery[i].pathToImage)
                    }

                    function addImageToPage(index, src) {
                        $("#imagesFromGallery").append(
                            $("<div>").attr({
                                class: "col"
                            }).prepend(
                                $("<img>").attr({
                                    class: "img-fluid",
                                    width: 300,
                                    style: "height: 200px",
                                    src: src,
                                    alt: "First slide"
                                })
                            )
                        )
                    }

                    function addImageToCarousel(index, src) {
                        $("#advertisementsCarouselIndicators").append(
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
                            $("#advertisementsCarouselItem").append(
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