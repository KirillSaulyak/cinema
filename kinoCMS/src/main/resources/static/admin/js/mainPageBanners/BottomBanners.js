$(document).ready(
    function () {
        if (oldBottomBanners.length > 0) {
            oldBottomBanners = JSON.parse(oldBottomBanners.replace(/&quot;/g, '"'));
        }

        if (oldBottomBannersSetting.length > 0) {
            oldBottomBannersSetting = JSON.parse(oldBottomBannersSetting.replace(/&quot;/g, '"'));
            $("#speedBottomBanners")[0].value = oldBottomBannersSetting.rotationSpeed;
            $("#enabledBottomBanners").attr("checked", oldBottomBannersSetting.enabled);
        }

        let mapOldBottomBanners = new Map();
        let countBottomBanners = 0;
        let deleteOldBanners = [];

        class OldBottomBanners {
            idNewsAndDealsBannersGalleries;
            pathToImage;
            url;

            constructor(id, srcImage, url) {
                this.idNewsAndDealsBannersGalleries = id;
                this.pathToImage = srcImage;
                this.url = url;
            }

            get id() {
                return this.idNewsAndDealsBannersGalleries;
            }

            set id(value) {
                this.idNewsAndDealsBannersGalleries = value;
            }

            get srcImage() {
                return this.pathToImage;
            }

            set srcImage(value) {
                this.pathToImage = value;
            }

            get url() {
                return this.url;
            }

            set url(value) {
                this.url = value;
            }

        }

        if (oldBottomBanners.length > 0) {
            for (let banner of oldBottomBanners) {
                mapOldBottomBanners.set(countBottomBanners.toString(), new OldBottomBanners(banner.idNewsAndDealsBannersGalleries,
                    banner.pathToImage, banner.url));
                addImageToGallery(banner.pathToImage, banner.url);
            }
        }


        let mapNewBottomBanners = new Map();

        class NewBottomBanners {
            fileImage;
            url;

            constructor(fileImage) {
                this.fileImage = fileImage;
            }

            set fileImg(fileImage) {
                return this.fileImage = fileImage;
            }

            set url(url) {
                this.url = url;
            }

            get fileImg() {
                return this.fileImage;
            }

            get url() {
                return this.url;
            }
        }

        $("#addToBottomBannersGalleryPicture").change(
            function () {
                $("#alertAddToGalleryPicture").remove();
                let reader = new FileReader();
                reader.onload = () => addImageToGallery(reader.result);
                reader.readAsDataURL(this.files[0]);
            }
        )

        function addImageToGallery(src, url) {
            if ($("#addToBottomBannersGalleryPicture")[0].files[0] != undefined) {
                mapNewBottomBanners.set(countBottomBanners.toString(),
                    new NewBottomBanners($("#addToBottomBannersGalleryPicture")[0].files[0]));
            }

            $("#imageBottomBannersGallery").before(
                $("<div>").attr({class: "col-4", id: "bottomBanner" + countBottomBanners}).prepend(
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
                        id: countBottomBanners
                    }).click(function (e) {
                        e.preventDefault();
                        if (!mapNewBottomBanners.delete(this.id)) {
                            deleteOldBanners.push(mapOldBottomBanners.get(this.id).id);
                            mapOldBottomBanners.delete(this.id);
                        } else {
                            $("#addToBottomBannersGalleryPicture")[0].value = "";
                        }
                        $("#bottomBanner" + this.id).detach();
                    }),
                    $("<div>").attr({class: "row"}).prepend(
                        $("<div>").attr({class: "col"}).prepend(
                            $("<input>").attr({
                                class: "form-control",
                                type: "url",
                                name: countBottomBanners,
                                placeholder: "URL",
                                value: url,
                                required: true
                            }).change(function () {
                                if (this.name < mapOldBottomBanners.size) {
                                    mapOldBottomBanners.get(this.name).url = this.value;
                                } else {
                                    mapNewBottomBanners.get(this.name).url = this.value
                                }
                                checkURL(this);
                            })
                        )
                    )
                )
            )
            countBottomBanners++;
        }

        function checkURL(url) {
            if (url.validity.typeMismatch) {
                $(url).after(
                    $("<div>").attr({
                        class: "alert alert-danger",
                        role: "alert",
                        name: "alertUrlLink",
                        id: "BottomAlertUrlLink" + url.name
                    }).text("Добавьте ссылку на трейлер")
                )
            } else {
                $("#BottomAlertUrlLink" + url.name).detach();
            }
        }

        $("#saveBottomBanners").click(
            function (e) {
                e.preventDefault();
                if ($("[name='BottomAlertUrlLink']").length === 0) {
                    let formData = new FormData;

                    for (let banner of mapOldBottomBanners.values()) {
                        formData.append("oldBottomBanners", JSON.stringify(banner));
                    }

                    for (let banner of mapNewBottomBanners.values()) {
                        formData.append("newBottomBanners", JSON.stringify(banner));
                    }

                    for (let deleteOldBanner of deleteOldBanners) {
                        formData.append("deleteOldBottomBannersById", deleteOldBanner);
                    }

                    formData.append("settingBottomBanners", JSON.stringify({
                        "rotationSpeed": $("#speedBottomBanners")[0].value,
                        "enabled": $("#enabledBottomBanners")[0].checked
                    }));

                    for (let image of mapNewBottomBanners.values()) {
                        formData.append("addToGalleryPicture", image.fileImage);
                    }

                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/edit-bottom-banners",
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
