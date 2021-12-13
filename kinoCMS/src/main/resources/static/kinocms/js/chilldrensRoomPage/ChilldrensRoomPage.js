$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-chilldrens-room-information",
                contentType: false,
                cache: false,
                processData: false,
                success: function (chilldrensRoomsInformation) {
                    let chilldrensRoom = JSON.parse(chilldrensRoomsInformation.chilldrensRoom);
                    let chilldrensRoomsGallery = JSON.parse(chilldrensRoomsInformation.chilldrensRoomsGallery);
                    let quantityPureImagesOnPage = 3;
                    $("#chilldrensRoomsName").prepend(chilldrensRoom.name);

                    $("#chilldrensRoomsDescription").prepend(chilldrensRoom.description);
                    $("#chilldrensRoomsPoster").attr("src", chilldrensRoom.pathToMainImage);
                    $("#chilldrensRoomsVideo").attr("src", chilldrensRoom.linkToVideo)

                    for (let i = 0; i < chilldrensRoomsGallery.length; i++) {
                        if (i < quantityPureImagesOnPage) {
                            addImageToPage(i, chilldrensRoomsGallery[i].pathToImage)
                        }
                        addImageToCarousel(i, chilldrensRoomsGallery[i].pathToImage)
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
                        $("#chilldrensRoomsCarouselIndicators").append(
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
                            $("#chilldrensRoomsCarouselItem").append(
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