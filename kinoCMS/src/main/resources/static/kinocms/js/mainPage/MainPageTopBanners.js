$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-top-banners-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (topBannersInformation) {
                    let setting = JSON.parse(topBannersInformation.setting);
                    if (setting.enabled) {
                        let galleriesWithInformation = JSON.parse(topBannersInformation.galleriesWithInformation);
                        for (let i = 0; i < galleriesWithInformation.length; i++) {
                            addImageToView(i, galleriesWithInformation[i].pathToImage,
                                galleriesWithInformation[i].description, galleriesWithInformation[i].url, setting.rotationSpeed)
                        }
                    } else {
                        $("#topBannersCarousel").attr("class", "d-none")
                    }

                    function addImageToView(index, src, description, url, speed) {
                        $("#topBannersCarouselIndicators").append(
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
                            $("#topBannersCarouselItem").append(
                                $("<div>").attr({
                                    class:
                                        function () {
                                            if (index === 0) {
                                                return " carousel-item active"
                                            } else {
                                                return "carousel-item"
                                            }
                                        },
                                    "data-interval": speed * 1000
                                }).prepend(
                                    $("<a>").attr("href", url).prepend(
                                        $("<img>").attr({
                                            class: "d-block ",
                                            style: "width: 100%;height: 400px",
                                            src: src,
                                            alt: "First slide"
                                        })
                                    ),
                                    $("<div>").attr("class", "carousel-caption d-none d-md-block").prepend(
                                        $("<p>").text(description)
                                    )
                                )
                            )
                    }

                }
            }
        )
    }
)
