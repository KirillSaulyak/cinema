$(document).ready(
    function () {

        $.ajax(
            {
                type: 'POST',
                url: "/send-all-news-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (newsInformation) {
                    let news = JSON.parse(newsInformation.news)
                    for (let i = 0; i < news.length; i++) {
                        if (news[i].enabled) {
                            newsToPage(i, news[i].name, news[i].description, news[i].pathToMainImage,
                                news[i].publicationDate)
                        }
                    }

                    function newsToPage(id, name, description, src, publicationDate) {
                        $("#news").prepend(
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
                                                window.location = "/news?idNews=" + news[this.id].idNews;
                                            }
                                        )
                                    )
                                ),
                                $("<div>").attr("class", "row").prepend(
                                    $("<div>").attr("class", "col d-flex justify-content-center").prepend(
                                        $("<a>").attr("href", "/news?idNews=" + news[id].idNews).prepend(
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