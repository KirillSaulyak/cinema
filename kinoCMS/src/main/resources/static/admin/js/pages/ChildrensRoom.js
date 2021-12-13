$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/admin/edit-default-entry-Детская комната",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (childrensRoom) {

                    let childrensRoomImagesGallery = new Map;
                    let mapOldChildrensRoomId = new Map;
                    let deleteOldChildrensRoomImagesFromGallery = [];
                    let countGalleriesImages = 0;

                    let childrensRoomsInformation = JSON.parse(childrensRoom.childrensRoomsInformation);
                    $("#enabledChildrensRoom")[0].checked = childrensRoomsInformation.enabled;

                    if (childrensRoomsInformation.description !== undefined) {
                        $("#childrensRoomDescription")[0].value = childrensRoomsInformation.description;
                    }

                    if (childrensRoomsInformation.pathToMainImage !== undefined) {

                        $("#mainImage").attr("src", childrensRoomsInformation.pathToMainImage);
                    }

                    let oldImagesFromGallery = JSON.parse(childrensRoom.childrensRoomsGallery);
                    for (let image of oldImagesFromGallery) {
                        mapOldChildrensRoomId.set(countGalleriesImages.toString(), image.idChildrensRoomsGallery)
                        addImageToGallery(image.pathToImage)
                    }

                    if (childrensRoomsInformation.pathToMainImage !== undefined) {
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
                            childrensRoomImagesGallery.set(countGalleriesImages.toString(),
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
                                id: "childrensRoomImageToGallery" + countGalleriesImages,
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
                                    if (!childrensRoomImagesGallery.delete(this.id)) {
                                        deleteOldChildrensRoomImagesFromGallery.push(mapOldChildrensRoomId.get(this.id));
                                        if (deleteOldChildrensRoomImagesFromGallery.length === mapOldChildrensRoomId.size) {
                                            $("#uploadImageToGallery").attr("required", true);
                                        }
                                    }
                                    $("#childrensRoomImageToGallery" + this.id).detach();
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

                    $("#saveChildrensRoom").click(
                        function (e) {
                            e.preventDefault();
                            if (isValidFields()) {
                                let formData = new FormData;
                                childrensRoomsInformation.description = $("#childrensRoomDescription")[0].value;
                                childrensRoomsInformation.enabled = $("#enabledChildrensRoom")[0].checked;
                                formData.append("childrensRoomsInformation", JSON.stringify(childrensRoomsInformation));

                                formData.append("mainImage", $("#uploadMainImage")[0].files[0]);

                                for (let image of childrensRoomImagesGallery.values()) {
                                    formData.append("childrensRoomsGallery", image);
                                }
                                for (let id of deleteOldChildrensRoomImagesFromGallery) {
                                    formData.append("deleteOldChildrensRoomsImagesFromGallery", id)
                                }

                                $.ajax(
                                    {
                                        type: 'POST',
                                        url: "/admin/save-default-entry-Детская комната",
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