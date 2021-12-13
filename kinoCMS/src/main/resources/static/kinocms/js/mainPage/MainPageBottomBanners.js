$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-bottom-banners-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (bottomBannersInformation) {
                    let setting = JSON.parse(bottomBannersInformation.setting);
                    if (setting.enabled) {
                        let galleriesWithInformation = JSON.parse(bottomBannersInformation.galleriesWithInformation);
                        for (let i = 0; i < galleriesWithInformation.length; i++) {
                            addImageToView(i, galleriesWithInformation[i].pathToImage,
                                galleriesWithInformation[i].url, setting.rotationSpeed)
                        }
                    }
                    else
                    {
                        $("#bottomBannersCarousel").attr("class","d-none")
                    }
                    function addImageToView(index, src, url, speed) {
                        $("#bottomBannersCarouselIndicators").append(
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
                            $("#bottomBannersCarouselItem").append(
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
                                    )
                                )
                            )
                    }

                }
            }
        )
    }
)
