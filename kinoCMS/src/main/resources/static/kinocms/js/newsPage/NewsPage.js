$(document).ready(
    function () {
        let formData = new FormData;
        formData.append("idNews", (new URLSearchParams(window.location.search).get("idNews")))

        $.ajax(
            {
                type: 'POST',
                url: "/send-chosen-news-information",
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (newsInformation) {
                    let deal = JSON.parse(newsInformation.deal);
                    let newsGallery = JSON.parse(newsInformation.newsGallery);

                    $("#newsName").prepend(deal.name);

                    $("#newsDescription").prepend(deal.description);
                    $("#newsPoster").attr("src", deal.pathToMainImage);
                    $("#newsVideo").attr("src", deal.linkToVideo)

                    for (let i = 0; i < newsGallery.length; i++) {
                        addImageToView(i, newsGallery[i].pathToImage,
                            newsGallery[i].url)
                    }

                    function addImageToView(index, src) {
                        $("#newsCarouselIndicators").append(
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
                            $("#newsCarouselItem").append(
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