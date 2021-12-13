$(document).ready(
    function () {
        let formData = new FormData;
        formData.append("idDeal", (new URLSearchParams(window.location.search).get("idDeal")))

        $.ajax(
            {
                type: 'POST',
                url: "/send-chosen-deal-information",
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (dealsInformation) {
                    let deal = JSON.parse(dealsInformation.deal);
                    let dealsGallery = JSON.parse(dealsInformation.dealsGallery);

                    $("#dealsName").prepend(deal.name);

                    $("#dealsDescription").prepend(deal.description);
                    $("#dealsPoster").attr("src", deal.pathToMainImage);
                    $("#dealsVideo").attr("src", deal.linkToVideo)

                    for (let i = 0; i < dealsGallery.length; i++) {
                        addImageToView(i, dealsGallery[i].pathToImage)
                    }

                    function addImageToView(index, src) {
                        $("#dealsCarouselIndicators").append(
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
                            $("#dealsCarouselItem").append(
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