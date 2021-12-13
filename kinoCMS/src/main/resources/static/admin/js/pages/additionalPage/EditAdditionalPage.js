$(document).ready(
    function () {
        class AdditionalPagesInformation {
            idAdditionalPage;
            name;
            description;
            enabled;

            constructor(idAdditionalPage, name, description, enabled) {
                this._idAdditionalPage = idAdditionalPage;
                this._name = name;
                this._description = description;
                this._enabled = enabled;
            }

            get idAdditionalPage() {
                return this._idAdditionalPage;
            }

            set idAdditionalPage(value) {
                this._idAdditionalPage = value;
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

            get enabled() {
                return this._enabled;
            }

            set enabled(value) {
                this._enabled = value;
            }
        }

        let newAdditionalPageInformation = new AdditionalPagesInformation();
        let additionalPageImagesGallery = new Map;


        let mapOldAdditionalPageImagesGalleryId = new Map;
        let deleteOldAdditionalPageImagesFromGallery = [];
        let countGalleriesImages = 0;

        oldAdditionalPage = JSON.parse(oldAdditionalPage.replace(/&quot;/g, '"'));

        $("#additionalPageName")[0].value = oldAdditionalPage.name;
        $("#additionalPageDescription")[0].value = oldAdditionalPage.description;
        $("#enabledAdditionalPage")[0].checked = oldAdditionalPage.enabled;
        $("#mainImage").attr("src", oldAdditionalPage.pathToMainImage);

        oldImagesFromGallery = JSON.parse(oldImagesFromGallery.replace(/&quot;/g, '"'));
        for (let image of oldImagesFromGallery) {
            mapOldAdditionalPageImagesGalleryId.set(countGalleriesImages.toString(), image.idAdditionalPagesGallery)
            addImageToGallery(image.pathToImage)
        }


        $("#uploadMainImage").attr("required", false);
        $("#uploadImageToGallery").attr("required", false);

        $("#additionalPageName").change(
            function () {
                newAdditionalPageInformation.name = this.value
            }
        )

        $("#additionalPageDescription").change(
            function () {
                newAdditionalPageInformation.description = this.value;
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
                additionalPageImagesGallery.set(countGalleriesImages.toString(),
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
                    id: "additionalPageImageToGallery" + countGalleriesImages,
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
                        if (!additionalPageImagesGallery.delete(this.id)) {
                            deleteOldAdditionalPageImagesFromGallery.push(mapOldAdditionalPageImagesGalleryId.get(this.id));
                            if (deleteOldAdditionalPageImagesFromGallery.length === mapOldAdditionalPageImagesGalleryId.size) {
                                $("#uploadImageToGallery").attr("required", true);
                            }
                        }
                        $("#additionalPageImageToGallery" + this.id).detach();
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

        $("#saveAdditionalPage").click(
            function (e) {
                e.preventDefault();
                if (isValidFields()) {
                    let formData = new FormData;
                    let newAdditionalPage = new AdditionalPagesInformation();
                    newAdditionalPage.idAdditionalPage = oldAdditionalPage.idAdditionalPage;
                    newAdditionalPage.name = $("#additionalPageName")[0].value;
                    newAdditionalPage.description = $("#additionalPageDescription")[0].value;
                    newAdditionalPage.enabled = $("#enabledAdditionalPage")[0].checked;
                    formData.append("additionalPagesInformation", JSON.stringify(newAdditionalPage));

                    formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                    for (let image of additionalPageImagesGallery.values()) {
                        formData.append("additionalPagesGallery", image);
                    }
                    for (let id of deleteOldAdditionalPageImagesFromGallery) {
                        formData.append("deleteOldAdditionalPageImagesFromGallery", id)
                    }

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/edit-additional-page",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function () {
                                alert("Сохранение прошло успешно")
                                window.location = "/admin/list-pages"
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