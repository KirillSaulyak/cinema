$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-vip-hall-information",
                contentType: false,
                cache: false,
                processData: false,
                success: function (vipHallsInformation) {
                    let vipHall = JSON.parse(vipHallsInformation.vipHall);
                    let vipHallsGallery = JSON.parse(vipHallsInformation.vipHallsGallery);
                    let quantityPureImagesOnPage = 3;
                    $("#vipHallsName").prepend(vipHall.name);

                    $("#vipHallsDescription").prepend(vipHall.description);
                    $("#vipHallsPoster").attr("src", vipHall.pathToMainImage);
                    $("#vipHallsVideo").attr("src", vipHall.linkToVideo)

                    for (let i = 0; i < vipHallsGallery.length; i++) {
                        if (i < quantityPureImagesOnPage) {
                            addImageToPage(i, vipHallsGallery[i].pathToImage)
                        }
                        addImageToCarousel(i, vipHallsGallery[i].pathToImage)
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
                        $("#vipHallsCarouselIndicators").append(
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
                            $("#vipHallsCarouselItem").append(
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