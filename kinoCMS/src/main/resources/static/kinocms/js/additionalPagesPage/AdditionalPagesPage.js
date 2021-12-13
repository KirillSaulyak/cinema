$(document).ready(
    function () {
        let formData = new FormData;
        formData.append("idAdditionalPage", (new URLSearchParams(window.location.search).get("idAdditionalPage")))

        $.ajax(
            {
                type: 'POST',
                url: "/send-chosen-additional-page-information",
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (additionalPagesInformation) {
                    let additionalPage = JSON.parse(additionalPagesInformation.additionalPage);
                    let additionalPagesGallery = JSON.parse(additionalPagesInformation.additionalPagesGallery);

                    $("#additionalPagesName").prepend(additionalPage.name);

                    $("#additionalPagesDescription").prepend(additionalPage.description);
                    $("#additionalPagesPoster").attr("src", additionalPage.pathToMainImage)

                    for (let i = 0; i < additionalPagesGallery.length; i++) {
                        addImageToView(i, additionalPagesGallery[i].pathToImage)
                    }

                    function addImageToView(index, src) {
                        $("#additionalPagesCarouselIndicators").append(
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
                            $("#additionalPagesCarouselItem").append(
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