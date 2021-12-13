$(document).ready(
    function () {
        class AdditionalPagesInformation {
            name;
            description;
            enabled;

            constructor(name, description, enabled) {
                this._name = name;
                this._description = description;
                this._enabled = enabled;
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

        let additionalPageImagesGallery = new Map;

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
                $("<div>").attr({class: "mt-2 mx-2", id: "additionalPageImageToGallery" + countGalleriesImages,}).prepend(
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
                        additionalPageImagesGallery.delete(this.id);
                        if (additionalPageImagesGallery.size === 0) {
                            $("#uploadImageToGallery")[0].value = ""
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
                    newAdditionalPage.name = $("#additionalPageName")[0].value;
                    newAdditionalPage.description = $("#additionalPageDescription")[0].value;
                    newAdditionalPage.enabled = $("#enabledAdditionalPage")[0].checked;

                    formData.append("additionalPagesInformation", JSON.stringify(newAdditionalPage));

                    formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                    for (let image of additionalPageImagesGallery.values()) {
                        formData.append("additionalPagesGallery", image);
                    }

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/create-additional-page",
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