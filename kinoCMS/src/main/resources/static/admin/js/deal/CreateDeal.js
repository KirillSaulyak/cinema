$(document).ready(
    function () {
        class DealsInformation {
            idDeal;
            name;
            description;
            publicationDate;
            linkToVideo
            enabled;

            constructor(idDeal, name, description, publicationDate, linkToVideo, enabled) {
                this._idDeal = idDeal;
                this._name = name;
                this._description = description;
                this._publicationDate = publicationDate;
                this._linkToVideo = linkToVideo;
                this._enabled = enabled;
            }

            get idDeal() {
                return this._idDeal;
            }

            set idDeal(value) {
                this._idDeal = value;
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

        let dealImagesGallery = new Map;

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
                dealImagesGallery.set(countGalleriesImages.toString(),
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
                $("<div>").attr({class: "mt-2 mx-2", id: "dealImageToGallery" + countGalleriesImages,}).prepend(
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
                        dealImagesGallery.delete(this.id);
                        if (dealImagesGallery.size === 0) {
                            $("#uploadImageToGallery")[0].value = ""
                        }
                        $("#dealImageToGallery" + this.id).detach();
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

        $("#saveDeal").click(
            function (e) {
                e.preventDefault();

                if (isValidFields()) {
                    let formData = new FormData;
                    let newDeal = new DealsInformation();
                    newDeal.publicationDate = $("#publicationsDate")[0].value
                    newDeal.name = $("#dealName")[0].value;
                    newDeal.description = $("#dealDescription")[0].value;
                    newDeal.linkToVideo = $("#dealLinkToVideo")[0].value;
                    newDeal.enabled = $("#enabledDeal")[0].checked;

                    formData.append("dealsInformation", JSON.stringify(newDeal));

                    formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                    for (let image of dealImagesGallery.values()) {
                        formData.append("dealsGallery", image);
                    }

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/create-deal",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function () {
                                alert("Сохранение прошло успешно")
                                window.location = "/admin/deals"
                            },
                            error: function () {
                                alert("Сохранить не удалось")
                            }
                        }
                    )
                }
            }
        )
    }
)