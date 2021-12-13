$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/admin/edit-default-entry-Реклама",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (advertisement) {

                    let advertisementImagesGallery = new Map;
                    let mapOldAdvertisementId = new Map;
                    let deleteOldAdvertisementImagesFromGallery = [];
                    let countGalleriesImages = 0;

                    let advertisementsInformation = JSON.parse(advertisement.advertisementsInformation);
                    $("#enabledAdvertisement")[0].checked = advertisementsInformation.enabled;

                    if (advertisementsInformation.description !== undefined) {
                        $("#advertisementDescription")[0].value = advertisementsInformation.description;
                    }

                    if (advertisementsInformation.pathToMainImage !== undefined) {

                        $("#mainImage").attr("src", advertisementsInformation.pathToMainImage);
                    }

                    let oldImagesFromGallery = JSON.parse(advertisement.advertisementsGallery);
                    for (let image of oldImagesFromGallery) {
                        mapOldAdvertisementId.set(countGalleriesImages.toString(), image.idAdvertisementsGallery)
                        addImageToGallery(image.pathToImage)
                    }

                    if (advertisementsInformation.pathToMainImage !== undefined) {
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
                            advertisementImagesGallery.set(countGalleriesImages.toString(),
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
                                id: "advertisementImageToGallery" + countGalleriesImages,
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
                                    if (!advertisementImagesGallery.delete(this.id)) {
                                        deleteOldAdvertisementImagesFromGallery.push(mapOldAdvertisementId.get(this.id));
                                        if (deleteOldAdvertisementImagesFromGallery.length === mapOldAdvertisementId.size) {
                                            $("#uploadImageToGallery").attr("required", true);
                                        }
                                    }
                                    $("#advertisementImageToGallery" + this.id).detach();
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

                    $("#saveAdvertisement").click(
                        function (e) {
                            e.preventDefault();
                            if (isValidFields()) {
                                let formData = new FormData;
                                advertisementsInformation.description = $("#advertisementDescription")[0].value;
                                advertisementsInformation.enabled = $("#enabledAdvertisement")[0].checked;
                                formData.append("advertisementsInformation", JSON.stringify(advertisementsInformation));

                                formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                                for (let image of advertisementImagesGallery.values()) {
                                    formData.append("advertisementsGallery", image);
                                }
                                for (let id of deleteOldAdvertisementImagesFromGallery) {
                                    formData.append("deleteOldAdvertisementsImagesFromGallery", id)
                                }

                                $.ajax(
                                    {
                                        type: 'POST',
                                        url: "/admin/save-default-entry-Реклама",
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