$(document).ready(
    function () {
        let formData = new FormData;
        formData.append("idHall", (new URLSearchParams(window.location.search).get("idHall")))

        $.ajax(
            {
                type: 'POST',
                url: "/send-chosen-hall-information",
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (hallsInformation) {
                    let hall = JSON.parse(hallsInformation.hall);
                    let hallsGallery = JSON.parse(hallsInformation.hallsGallery);

                    $("[name ='schedule']").click(
                        function (e) {
                            e.preventDefault();
                            window.location = "/schedule";
                        }
                    )

                    $("#hallsTopBanner").attr("src", hall.pathToTopBanner)

                    $("#hallsName").prepend(hall.name);

                    $("#hallsDescription").prepend(hall.description);
                    $("#hallsLayout").attr("src", hall.pathToHallLayout)

                    for (let i = 0; i < hallsGallery.length; i++) {
                        addImageToView(i, hallsGallery[i].pathToImage)
                    }


                    function addImageToView(index, src) {
                        $("#hallsGalleryCarouselIndicators").append(
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
                            $("#hallsGalleryCarouselItem").append(
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