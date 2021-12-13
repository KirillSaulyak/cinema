$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/admin/edit-default-entry-О кинотеатре",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (descriptionCurrentCinema) {

                    let descriptionCurrentCinemaImagesGallery = new Map;
                    let mapOldDescriptionCurrentCinemaImagesGalleryId = new Map;
                    let deleteOldDescriptionCurrentCinemaImagesFromGallery = [];
                    let countGalleriesImages = 0;

                    let descriptionCurrentCinemaInformation = JSON.parse(descriptionCurrentCinema.descriptionCurrentCinemasInformation);
                    $("#enabledDescriptionCurrentCinema")[0].checked = descriptionCurrentCinemaInformation.enabled;

                    if (descriptionCurrentCinemaInformation.description !== undefined) {
                        $("#descriptionCurrentCinemaDescription")[0].value = descriptionCurrentCinemaInformation.description;
                    }

                    if (descriptionCurrentCinemaInformation.pathToMainImage !== undefined) {

                        $("#mainImage").attr("src", descriptionCurrentCinemaInformation.pathToMainImage);
                    }

                    let oldImagesFromGallery = JSON.parse(descriptionCurrentCinema.descriptionCurrentCinemasGallery);
                    for (let image of oldImagesFromGallery) {
                        mapOldDescriptionCurrentCinemaImagesGalleryId.set(countGalleriesImages.toString(), image.idDescriptionCurrentCinemasGallery)
                        addImageToGallery(image.pathToImage)
                    }

                    if (descriptionCurrentCinemaInformation.pathToMainImage !== undefined) {
                        $("#uploadMainImage").attr("required", false);
                    }

                    $("#uploadImageToGallery").attr("required", false);

                    $("#uploadMainImage").change(
                        function () {
                            let reader = new FileReader();
                            reader.onload = () => $("#mainImage").attr("src", reader.result);
                            reader.readAsDataURL(this.files[0]);
                        }
                    )

                    $("#uploadImageToGallery").change(
                        function () {
                            descriptionCurrentCinemaImagesGallery.set(countGalleriesImages.toString(),
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
                                id: "descriptionCurrentCinemaImageToGallery" + countGalleriesImages,
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
                                    if (!descriptionCurrentCinemaImagesGallery.delete(this.id)) {
                                        deleteOldDescriptionCurrentCinemaImagesFromGallery.push(mapOldDescriptionCurrentCinemaImagesGalleryId.get(this.id));
                                        if (deleteOldDescriptionCurrentCinemaImagesFromGallery.length === mapOldDescriptionCurrentCinemaImagesGalleryId.size) {
                                            $("#uploadImageToGallery").attr("required", true);
                                        }
                                    }
                                    $("#descriptionCurrentCinemaImageToGallery" + this.id).detach();
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

                    $("#saveDescriptionCurrentCinema").click(
                        function (e) {
                            e.preventDefault();
                            if (isValidFields()) {
                                let formData = new FormData;
                                descriptionCurrentCinemaInformation.description = $("#descriptionCurrentCinemaDescription")[0].value;
                                descriptionCurrentCinemaInformation.enabled = $("#enabledDescriptionCurrentCinema")[0].checked;
                                 formData.append("descriptionCurrentCinemasInformation", JSON.stringify(descriptionCurrentCinemaInformation));

                                formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                                for (let image of descriptionCurrentCinemaImagesGallery.values()) {
                                    formData.append("descriptionCurrentCinemasGallery", image);
                                }
                                for (let id of deleteOldDescriptionCurrentCinemaImagesFromGallery) {
                                    formData.append("deleteOldDescriptionCurrentCinemaImagesFromGallery", id)
                                }

                                $.ajax(
                                    {
                                        type: 'POST',
                                        url: "/admin/save-default-entry-О кинотеатре",
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
            }
        )
    }
)