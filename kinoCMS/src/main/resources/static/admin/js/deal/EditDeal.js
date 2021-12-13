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

        let newDealInformation = new DealsInformation();
        let dealImagesGallery = new Map;


        let mapOldDealImagesGalleryId = new Map;
        let deleteOldDealImagesFromGallery = [];
        let countGalleriesImages = 0;

        oldDeal = JSON.parse(oldDeal.replace(/&quot;/g, '"'));

        $("#dealName")[0].value = oldDeal.name;
        $("#dealDescription")[0].value = oldDeal.description;
        $("#publicationsDate")[0].value = oldDeal.publicationDate;
        $("#dealLinkToVideo")[0].value = oldDeal.linkToVideo;
        $("#enabledDeal")[0].checked = oldDeal.enabled;
        $("#mainImage").attr("src", oldDeal.pathToMainImage);

        oldImagesFromGallery = JSON.parse(oldImagesFromGallery.replace(/&quot;/g, '"'));
        for (let image of oldImagesFromGallery) {
            mapOldDealImagesGalleryId.set(countGalleriesImages.toString(), image.idDealGallery)
            addImageToGallery(image.pathToImage)
        }


        $("#uploadMainImage").attr("required", false);
        $("#uploadImageToGallery").attr("required", false);

        $("#dealName").change(
            function () {
                newDealInformation.name = this.value
            }
        )

        $("#dealDescription").change(
            function () {
                newDealInformation.description = this.value;
            }
        )

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
                $("<div>").attr({
                    class: "mt-2 mx-2",
                    id: "dealImageToGallery" + countGalleriesImages,
                }).prepend(
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
                        if (!dealImagesGallery.delete(this.id)) {
                            deleteOldDealImagesFromGallery.push(mapOldDealImagesGalleryId.get(this.id));
                            if (deleteOldDealImagesFromGallery.length === mapOldDealImagesGalleryId.size) {
                                $("#uploadImageToGallery").attr("required", true);
                            }
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
                    newDeal.idDeal = oldDeal.idDeal;
                    newDeal.publicationDate = $("#publicationsDate")[0].value;
                    newDeal.name = $("#dealName")[0].value;
                    newDeal.description = $("#dealDescription")[0].value;
                    newDeal.linkToVideo = $("#dealLinkToVideo")[0].value;
                    newDeal.enabled = $("#enabledDeal")[0].checked;
                    formData.append("dealsInformation", JSON.stringify(newDeal));

                    formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                    for (let image of dealImagesGallery.values()) {
                        formData.append("dealsGallery", image);
                    }
                    for (let id of deleteOldDealImagesFromGallery) {
                        formData.append("deleteOldDealImagesFromGallery", id)
                    }

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/edit-deal",
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