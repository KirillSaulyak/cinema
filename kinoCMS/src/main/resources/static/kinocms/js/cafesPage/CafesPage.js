$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-cafe-information",
                contentType: false,
                cache: false,
                processData: false,
                success: function (cafesInformation) {
                    let cafe = JSON.parse(cafesInformation.cafe);
                    let cafesGallery = JSON.parse(cafesInformation.cafesGallery);

                    $("#cafesName").prepend(cafe.name);

                    $("#cafesDescription").prepend(cafe.description);
                    $("#cafesPoster").attr("src", cafe.pathToMainImage);
                    $("#cafesVideo").attr("src", cafe.linkToVideo)

                    for (let i = 0; i < cafesGallery.length; i++) {
                        addImageToView(i, cafesGallery[i].pathToImage,
                            cafesGallery[i].url)
                    }

                    function addImageToView(index, src) {
                        $("#imagesFromGallery").append(
                            $("<div>").attr({
                                class:"col"
                            }).prepend(
                                $("<img>").attr({
                                    class: "img-fluid",
                                    width: 300,
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