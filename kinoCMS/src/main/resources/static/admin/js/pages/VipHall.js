$(document).ready(
    function () {
        $(document).ready(
            function () {
                $.ajax(
                    {
                        type: 'POST',
                        url: "/admin/edit-default-entry-Vip-зал",
                        contentType: JSON,
                        cache: false,
                        processData: false,
                        success: function (vipHall) {

                            let vipHallImagesGallery = new Map;
                            let mapOldVipHallId = new Map;
                            let deleteOldVipHallImagesFromGallery = [];
                            let countGalleriesImages = 0;

                            let vipHallsInformation = JSON.parse(vipHall.vipHallsInformation);
                            $("#enabledVipHall")[0].checked = vipHallsInformation.enabled;

                            if (vipHallsInformation.description !== undefined) {
                                $("#vipHallDescription")[0].value = vipHallsInformation.description;
                            }

                            if (vipHallsInformation.pathToMainImage !== undefined) {

                                $("#mainImage").attr("src", vipHallsInformation.pathToMainImage);
                            }

                            let oldImagesFromGallery = JSON.parse(vipHall.vipHallsGallery);
                            for (let image of oldImagesFromGallery) {
                                mapOldVipHallId.set(countGalleriesImages.toString(), image.idVipHallsGallery)
                                addImageToGallery(image.pathToImage)
                            }

                            if (vipHallsInformation.pathToMainImage !== undefined) {
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
                                    vipHallImagesGallery.set(countGalleriesImages.toString(),
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
                                        id: "vipHallImageToGallery" + countGalleriesImages,
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
                                            if (!vipHallImagesGallery.delete(this.id)) {
                                                deleteOldVipHallImagesFromGallery.push(mapOldVipHallId.get(this.id));
                                                if (deleteOldVipHallImagesFromGallery.length === mapOldVipHallId.size) {
                                                    $("#uploadImageToGallery").attr("required", true);
                                                }
                                            }
                                            $("#vipHallImageToGallery" + this.id).detach();
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

                            $("#saveVipHall").click(
                                function (e) {
                                    e.preventDefault();
                                    if (isValidFields()) {
                                        let formData = new FormData;
                                        vipHallsInformation.description = $("#vipHallDescription")[0].value;
                                        vipHallsInformation.enabled = $("#enabledVipHall")[0].checked;
                                        formData.append("vipHallsInformation", JSON.stringify(vipHallsInformation));

                                        formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                                        for (let image of vipHallImagesGallery.values()) {
                                            formData.append("vipHallsGallery", image);
                                        }
                                        for (let id of deleteOldVipHallImagesFromGallery) {
                                            formData.append("deleteOldVipHallsImagesFromGallery", id)
                                        }

                                        $.ajax(
                                            {
                                                type: 'POST',
                                                url: "/admin/save-default-entry-Vip-зал",
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
    }
)