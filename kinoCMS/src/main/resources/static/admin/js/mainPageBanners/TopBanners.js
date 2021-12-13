$(document).ready(
    function () {
        if (oldTopBanner.length > 0) {
            oldTopBanner = JSON.parse(oldTopBanner.replace(/&quot;/g, '"'));
        }
        if (oldTopBannersSetting.length > 0) {
            oldTopBannersSetting = JSON.parse(oldTopBannersSetting.replace(/&quot;/g, '"'));
            $("#speedTopBanners")[0].value = oldTopBannersSetting.rotationSpeed;
            $("#enabledTopBanners").attr("checked", oldTopBannersSetting.enabled);
        }
        let mapOldTopBanners = new Map();
        let countTopBanners = 0;
        let deleteOldBanners = [];

        class OldTopBanners {
            idTopBannersGallery;
            pathToImage;
            url;
            description;

            constructor(id, srcImage, url, description) {
                this.idTopBannersGallery = id;
                this.pathToImage = srcImage;
                this.url = url;
                this.description = description;
            }

            get id() {
                return this.idTopBannersGallery;
            }

            set id(value) {
                this.idTopBannersGallery = value;
            }

            get url() {
                return this.url;
            }

            set url(value) {
                this.url = value;
            }

            get description() {
                return this.description;
            }

            set description(value) {
                this.description = value;
            }
        }

        if (oldTopBanner.length > 0) {
            for (let banner of oldTopBanner) {
                mapOldTopBanners.set(countTopBanners.toString(), new OldTopBanners(banner.idTopBannersGallery,
                    banner.pathToImage, banner.url, banner.description));
                addImageToGallery(banner.pathToImage, banner.url, banner.description);
            }
        }


        let mapNewTopBanners = new Map();

        class NewTopBanners {
            fileImage;
            url;
            description;

            constructor(fileImage) {
                this.fileImage = fileImage;
            }

            set url(url) {
                this.url = url;
            }

            set description(description) {
                return this.description = description;
            }

            get url() {
                return this.url;
            }
        }

        $("#addToTopBannersGalleryPicture").change(
            function () {
                $("#alertAddToGalleryPicture").remove();
                let reader = new FileReader();
                reader.onload = () => addImageToGallery(reader.result);
                reader.readAsDataURL(this.files[0]);
            }
        )

        function addImageToGallery(src, url, description) {
            if ($("#addToTopBannersGalleryPicture")[0].files[0] != undefined) {
                mapNewTopBanners.set(countTopBanners.toString(),
                    new NewTopBanners($("#addToTopBannersGalleryPicture")[0].files[0]));
            }

            $("#imagesTopBannersGallery").before(
                $("<div>").attr({class: "col-4", id: "topBanner" + countTopBanners}).prepend(
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
                        id: countTopBanners
                    }).click(function (e) {
                        e.preventDefault();
                        if (!mapNewTopBanners.delete(this.id)) {
                            deleteOldBanners.push(mapOldTopBanners.get(this.id).id);
                            mapOldTopBanners.delete(this.id);
                        } else {
                            $("#addToTopBannersGalleryPicture")[0].value = "";
                        }
                        $("#topBanner" + this.id).detach();
                    }),
                    $("<div>").attr({class: "row"}).prepend(
                        $("<div>").attr({class: "col"}).prepend(
                            $("<input>").attr({
                                class: "form-control",
                                type: "url",
                                name: countTopBanners,
                                placeholder: "URL",
                                value: url,
                                required: true
                            }).change(function () {
                                if (this.name < mapOldTopBanners.size) {
                                    mapOldTopBanners.get(this.name).url = this.value;
                                } else {
                                    mapNewTopBanners.get(this.name).url = this.value
                                }
                                checkURL(this);
                            }),
                            $("<input>").attr({
                                class: "form-control mb-3",
                                type: "text",
                                name: countTopBanners,
                                placeholder: "Текст",
                                value: description,
                                required: true
                            }).change(function () {
                                if (this.name < mapOldTopBanners.size) {
                                    mapOldTopBanners.get(this.name).description = this.value
                                } else {
                                    mapNewTopBanners.get(this.name).description = this.value
                                }
                            })
                        )
                    )
                )
            )
            countTopBanners++;
        }

        function checkURL(url) {
            if (url.validity.typeMismatch) {
                $(url).after(
                    $("<div>").attr({
                        class: "alert alert-danger",
                        role: "alert",
                        name: "alertUrlLink",
                        id: "TopAlertUrlLink" + url.name
                    }).text("Добавьте ссылку на трейлер")
                )
            } else {
                $("#TopAlertUrlLink" + url.name).detach();
            }
        }

        $("#saveTopBanners").click(
            function (e) {
                e.preventDefault();
                if ($("[name='TopAlertUrlLink']").length === 0) {
                    let formData = new FormData;

                    for (let banner of mapOldTopBanners.values()) {
                        formData.append("oldTopBanners", JSON.stringify(banner));
                    }

                    for (let banner of mapNewTopBanners.values()) {
                        formData.append("newTopBanners", JSON.stringify(banner));
                    }

                    for (let deleteOldBanner of deleteOldBanners) {
                        formData.append("deleteOldTopBannersById", deleteOldBanner);
                    }

                    formData.append("settingTopBanners", JSON.stringify({
                        "rotationSpeed": $("#speedTopBanners")[0].value,
                        "enabled": $("#enabledTopBanners")[0].checked
                    }));

                    for (let image of mapNewTopBanners.values()) {
                        formData.append("addToTopBannersGalleryPicture", image.fileImage);
                    }

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/edit-top-banners",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function () {
                                alert("Сохранение прошло успешно")
                                window.location = "/admin/edit-banners"
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
