$(document).ready(
    function () {

        $.ajax(
            {
                type: 'POST',
                url: "/send-all-deals-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (dealsInformation) {
                    let deals = JSON.parse(dealsInformation.deals)
                    for (let i = 0; i < deals.length; i++) {
                        if (deals[i].enabled) {
                            dealToPage(i, deals[i].name, deals[i].description, deals[i].pathToMainImage,
                                deals[i].publicationDate)
                        }
                    }

                    function dealToPage(id, name, description, src, publicationDate) {
                        $("#deals").prepend(
                            $("<div>").attr("class", "col mx-3").prepend(
                                $("<div>").attr("class", "row").prepend(
                                    $("<div>").attr("class", "col d-flex justify-content-center").prepend(
                                        $("<img>").attr({
                                            class: "img-thumbnail",
                                            src: src,
                                            width: 200,
                                            id: id,
                                            alt: "Thumbnail image"
                                        }).click(
                                            function (e) {
                                                e.preventDefault();
                                                window.location = "/deal?idDeal=" + deals[this.id].idDeal;
                                            }
                                        )
                                    )
                                ),
                                $("<div>").attr("class", "row").prepend(
                                    $("<div>").attr("class", "col d-flex justify-content-center").prepend(
                                        $("<a>").attr("href", "/deal?idDeal=" + deals[id].idDeal).prepend(
                                            $("<p>").attr("class", "text-center").text(name)
                                        )
                                    )
                                ),
                                $("<div>").attr("class", "row").prepend(
                                    $("<div>").attr("class", "col d-flex justify-content-center").prepend(
                                        $("<p>").attr("class", "text-center").text(publicationDate)
                                    )
                                ),
                                $("<div>").attr("class", "row d-flex justify-content-center").prepend(
                                    $("<div>").attr("class", "col-5").prepend(
                                        $("<p>").attr("class", "text-center").text(description)
                                    )
                                )
                            )
                        )
                    }
                }
            }
        )
    }
)