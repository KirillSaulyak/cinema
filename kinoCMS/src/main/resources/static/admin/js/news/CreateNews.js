$(document).ready(
    function () {
        class NewsInformation {
            idNews;
            name;
            description;
            publicationDate;
            linkToVideo
            enabled;

            constructor(idNews, name, description, publicationDate, linkToVideo, enabled) {
                this._idNews = idNews;
                this._name = name;
                this._description = description;
                this._publicationDate = publicationDate;
                this._linkToVideo = linkToVideo;
                this._enabled = enabled;
            }

            get idNews() {
                return this._idNews;
            }

            set idNews(value) {
                this._idNews = value;
            }

            get name() {
                return this._name;
            }

            set name(value) {
                this._name = value;
            }

            get description() {
                return this._description;
            }

            set description(value) {
                this._description = value;
            }

            get publicationDate() {
                return this._publicationDate;
            }

            set publicationDate(value) {
                this._publicationDate = value;
            }

            get linkToVideo() {
                return this._linkToVideo;
            }

            set linkToVideo(value) {
                this._linkToVideo = value;
            }

            get enabled() {
                return this._enabled;
            }

            set enabled(value) {
                this._enabled = value;
            }
        }

        let newsImagesGallery = new Map;

        let countGalleriesImages = 0;

        $("#uploadMainImage").change(
            function () {
                let reader = new FileReader();
                reader.onload = () => $("#mainImage").attr("src", reader.result);
                reader.readAsDataURL(this.files[0]);
            }
        )

        $("#uploadImageToGallery").change(
            function () {
                newsImagesGallery.set(countGalleriesImages.toString(),
                    $("#uploadImageToGallery")[0].files[0]);
                let reader = new FileReader();
                reader.onload = function () {
                    addImageToGallery(reader.result, countGalleriesImages);
                }
                reader.readAsDataURL(this.files[0]);
            }
        )

        function addImageToGallery(src) {
            $("#imagesGallery").before(
                $("<div>").attr({class: "mt-2 mx-2", id: "newsImageToGallery" + countGalleriesImages,}).prepend(
                    $('<img>').attr({
                        class: "img-thumbnail",
                        style: "height: 120px; max-width: 160px",
                        src: src,
                        alt: "default or upload img"
                    }),

                    $('<img>').attr({
                        class: "img-fluid align-top mr-2",
                        src: "images/close.svg",
                        alt: "close",
                        id: countGalleriesImages
                    }).click(function (e) {
                        e.preventDefault();
                        newsImagesGallery.delete(this.id);
                        if (newsImagesGallery.size === 0) {
                            $("#uploadImageToGallery")[0].value = ""
                        }
                        $("#newsImageToGallery" + this.id).detach();
                    }),
                )
            )
            countGalleriesImages++;
        }

        function isValidFields() {
            let requiredFields = $(":required");
            for (let field of requiredFields) {
                if (field.validity.valueMissing || field.validity.typeMismatch) {
                    $("#container")[0].classList.add("was-validated");
                    return false;
                }
            }
            return true;
        }

        $("#saveNews").click(
            function (e) {
                e.preventDefault();

                if (isValidFields()) {
                    let formData = new FormData;
                    let newNews = new NewsInformation();
                    newNews.publicationDate = $("#publicationsDate")[0].value
                    newNews.name = $("#newsName")[0].value;
                    newNews.description = $("#newsDescription")[0].value;
                    newNews.linkToVideo = $("#newsLinkToVideo")[0].value;
                    newNews.enabled = $("#enabledNews")[0].checked;

                    formData.append("newsInformation", JSON.stringify(newNews));

                    formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                    for (let image of newsImagesGallery.values()) {
                        formData.append("newsGallery", image);
                    }

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/create-news",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function () {
                                alert("???????????????????? ???????????? ??????????????")
                                window.location = "/admin/news"
                            },
                            error: function () {
                                alert("?????????????????? ???? ??????????????")
                            }
                        }
                    )
                }
            }
        )
    }
)