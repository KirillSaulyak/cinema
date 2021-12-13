$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/admin/edit-default-entry-Кафе-бар",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (cafe) {

                    let cafeImagesGallery = new Map;
                    let mapOldCafeId = new Map;
                    let deleteOldCafeImagesFromGallery = [];
                    let countGalleriesImages = 0;

                    let cafesInformation = JSON.parse(cafe.cafesInformation);
                    $("#enabledCafe")[0].checked = cafesInformation.enabled;

                    if (cafesInformation.description !== undefined) {
                        $("#cafeDescription")[0].value = cafesInformation.description;
                    }

                    if (cafesInformation.pathToMainImage !== undefined) {

                        $("#mainImage").attr("src", cafesInformation.pathToMainImage);
                    }

                    let oldImagesFromGallery = JSON.parse(cafe.cafesGallery);
                    for (let image of oldImagesFromGallery) {
                        mapOldCafeId.set(countGalleriesImages.toString(), image.idCafesGallery)
                        addImageToGallery(image.pathToImage)
                    }

                    if (cafesInformation.pathToMainImage !== undefined) {
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
                            cafeImagesGallery.set(countGalleriesImages.toString(),
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
                                id: "cafeImageToGallery" + countGalleriesImages,
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
                                    if (!cafeImagesGallery.delete(this.id)) {
                                        deleteOldCafeImagesFromGallery.push(mapOldCafeId.get(this.id));
                                        if (deleteOldCafeImagesFromGallery.length === mapOldCafeId.size) {
                                            $("#uploadImageToGallery").attr("required", true);
                                        }
                                    }
                                    $("#cafeImageToGallery" + this.id).detach();
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

                    $("#saveCafe").click(
                        function (e) {
                            e.preventDefault();
                            if (isValidFields()) {
                                let formData = new FormData;
                                cafesInformation.description = $("#cafeDescription")[0].value;
                                cafesInformation.enabled = $("#enabledCafe")[0].checked;
                                formData.append("cafesInformation", JSON.stringify(cafesInformation));

                                formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                                for (let image of cafeImagesGallery.values()) {
                                    formData.append("cafesGallery", image);
                                }
                                for (let id of deleteOldCafeImagesFromGallery) {
                                    formData.append("deleteOldCafesImagesFromGallery", id)
                                }

                                $.ajax(
                                    {
                                        type: 'POST',
                                        url: "/admin/save-default-entry-Кафе-бар",
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