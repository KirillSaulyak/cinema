$(document).ready(
    function () {
        let moviesNameReady = false;
        let moviesTypeReady = false;
        let moviesDescriptionReady = false;
        let trailerLinkReady = false;
        let mainImgReady = false;
        let updateMovie = false;
        let imageGallery = new Map();
        let countImagesGallery = 0;
        let deleteOldImagesGallery = [];

        $("#uploadMainImg").change(
            function () {
                let reader = new FileReader();
                reader.onload = () => $('#mainImage').attr('src', reader.result);
                reader.readAsDataURL(this.files[0]);
                $("#alertMainImg").remove();
                mainImgReady = true;
            }
        )

        $("#addToGalleryPicture").change(
            function () {
                $("#alertAddToGalleryPicture").remove();
                let reader = new FileReader();
                reader.onload = () => addImageToGallery(reader.result);
                reader.readAsDataURL(this.files[0]);
            }
        )

        function addImageToGallery(src) {
            if ($("#addToGalleryPicture")[0].files[0] != undefined) {
                imageGallery.set(countImagesGallery.toString(), $("#addToGalleryPicture")[0].files[0]);
            }
            $("#imagesGallery").prepend(
                $('<img>').attr({
                    class: "img-thumbnail mb-3",
                    src: src,
                    id: "imageGallery" + countImagesGallery.toString(),
                    alt: "default or upload img",
                    style: "height: 120px; max-width: 160px",
                }),
                $('<img>').attr({
                    class: "img-fluid align-top mr-2",
                    src: "images/close.svg",
                    alt: "close",
                    id: countImagesGallery
                }).click(function (e) {
                    e.preventDefault();
                    if (!imageGallery.delete(this.id)) {
                        deleteOldImagesGallery.push($("#imageGallery" + this.id).attr("src"))
                    } else {
                        $("#addToGalleryPicture")[0].value = "";
                    }
                    $(this).detach();
                    $("#imageGallery" + this.id).detach();

                    if (imageGallery.size === 0 && (pathToGalleryImages.length === 0
                        || deleteOldImagesGallery.length === pathToGalleryImages.length)) {
                        $("#addToGalleryPicture").attr("required", true)
                        checkGallery();
                    }
                })
            )
            countImagesGallery++;
        }

        if (moviesTypes.length > 0) {
            for (let type of moviesTypes) {
                $("#checkboxType" + type.trim()).attr("checked", true)
            }
            allTrue();
        }
        if (pathToMainImage.length > 0) {
            $("#uploadMainImg").attr("required", false)
        }

        if (pathToGalleryImages.length > 0) {
            for (let path of pathToGalleryImages) {
                addImageToGallery(path.trim())
            }
            $("#addToGalleryPicture").attr("required", false)
        }

        function checkMoviesName() {
            if ($("#moviesName")[0].validity.valueMissing && $("#alertMoviesName").length === 0) {
                $("#moviesName").after(
                    '<div class = "alert alert-danger w-50" role= "alert"  id= "alertMoviesName"> ' +
                    'Введите название фильма</div>'
                )
                moviesNameReady = false;
            }
        }

        $("#moviesName").blur(
            () => checkMoviesName()
        )

        $("#moviesName").on('change keypress',
            function () {
                $("#alertMoviesName").remove();
                moviesNameReady = true;
            }
        )

        function checkMoviesType() {
            if (!$('[name ="moviesType"]').is(":checked")
                && $("#alertTypeMovie").length === 0) {
                $("#checkboxes").after('<div class = "alert alert-danger w-50" role= "alert"' +
                    ' id= "alertTypeMovie">Выберите минимум один тип фильма</div>')
                moviesTypeReady = false;
            }
        }

        $('[name ="moviesType"]').change(() => checkMoviesType())

        $('[name ="moviesType"]').click(
            function () {
                $("#alertTypeMovie").remove();
                moviesTypeReady = true;
            }
        )

        function checkMoviesDescription() {
            if ($("#moviesDescription")[0].validity.valueMissing && $("#alertMoviesDescription").length === 0) {
                $("#moviesDescription").after(
                    '<div class = "alert alert-danger w-50" role= "alert"  id= "alertMoviesDescription"> ' +
                    'Добавьте описание фильма</div>'
                )
                moviesDescriptionReady = false;
            }

        }

        $("#moviesDescription").blur(
            () => checkMoviesDescription()
        )

        $("#moviesDescription").on('change keypress',
            function () {
                $("#alertMoviesDescription").remove();
                moviesDescriptionReady = true;
            }
        )

        function checkMainImage() {
            if ($("#uploadMainImg")[0].validity.valueMissing && $("#alertMainImg").length === 0) {
                $("#uploadMainImg").after(
                    '<div class = "alert alert-danger w-50" role= "alert"  id= "alertMainImg"> ' +
                    'Загрузите главную картинку</div>'
                )
                mainImgReady = false;
            }
        }

        function checkTrailerLink() {
            if (($("#trailerLink")[0].validity.typeMismatch || $("#trailerLink")[0].validity.valueMissing)
                && $("#alertTrailerLink").length === 0) {
                $("#trailerLink").after(
                    '<div class = "alert alert-danger w-50" role= "alert"  id= "alertTrailerLink"> ' +
                    'Добавьте ссылку на трейлер</div>'
                )
                trailerLinkReady = false;
            }
        }

        $("#trailerLink").blur(
            () => checkTrailerLink()
        )

        $("#trailerLink").on('change keypress',
            function () {
                $("#alertTrailerLink").remove();
                trailerLinkReady = true;
            }
        )

        function checkGallery() {
            if ($("#addToGalleryPicture")[0].validity.valueMissing && $("#alertAddToGalleryPicture").length === 0) {
                $("#addToGalleryPicture").after(
                    '<div class = "alert alert-danger w-50" role= "alert"  id= "alertAddToGalleryPicture"> ' +
                    'Загрузите изображение в галерею</div>'
                )
            }
        }

        function allTrue() {
            moviesNameReady = true;
            moviesTypeReady = true;
            moviesDescriptionReady = true;
            mainImgReady = true;
            trailerLinkReady = true;
            updateMovie = true;
        }

        $("#save").click(
            function (e) {
                e.preventDefault();
                if (!moviesNameReady || !moviesTypeReady || !moviesDescriptionReady || !mainImgReady
                    || !trailerLinkReady || imageGallery.size === 0 && (pathToGalleryImages.length === 0
                        || deleteOldImagesGallery.length === pathToGalleryImages.length)) {
                    checkMoviesName();
                    checkMoviesType();
                    checkMoviesDescription();
                    checkMainImage();
                    checkTrailerLink();
                    checkGallery();
                } else {
                    let formData = new FormData($("#mainForm")[0]);
                    formData.delete("addToGalleryPicture");
                    for (let image of imageGallery.values()) {
                        formData.append("addToGalleryPicture", image);
                    }
                    let url;
                    if (updateMovie === true) {
                        url = "/admin/update-movie";
                        formData.append("moviesId", moviesId);
                        for (let path of deleteOldImagesGallery) {
                            formData.append("oldImagesGallery", path)
                        }
                    } else {
                        url = "/admin/create-movie";
                    }
                    $.ajax(
                        {
                            type: 'POST',
                            url: url,
                            data: formData,
                            cache: false,
                            contentType: false,
                            processData: false,
                            success: function () {
                                alert("Сохранение прошло успешно")
                                window.location = "/admin/movies"
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
