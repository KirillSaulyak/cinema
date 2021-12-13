$(document).ready(
    function () {
        class BackgroundBanner {
            pathToBackgroundBanner
            enabled;

            constructor(pathToBackgroundBanner, enabled) {
                this.pathToBackgroundBanner = pathToBackgroundBanner;
                this.enabled = enabled;
            }

            get pathToBackgroundBanner() {
                return this.pathToBackgroundBanner;
            }

            set pathToBackgroundBanner(value) {
                this.pathToBackgroundBanner = value;
            }

            get enabled() {
                return this.enabled;
            }

            set enabled(value) {
                this.enabled = value;
            }
        }

        if (oldBackgroundBannerSetting.length > 0) {
            let oldBackgroundBanner = JSON.parse(oldBackgroundBannerSetting.replace(/&quot;/g, '"'));
            $('#previewBackgroundBanner').attr('src', oldBackgroundBanner.pathToBackgroundBanner)
            $("#enabledBackgroundBanner").attr("checked", oldBackgroundBanner.enabled)
        }

        $("#backgroundBanner").change(
            function () {
                let reader = new FileReader();
                reader.onload = () => $('#previewBackgroundBanner').attr('src', reader.result);
                reader.readAsDataURL(this.files[0]);
            }
        )
        $("#saveBackgroundBanner").click
        (
            function (e) {
                e.preventDefault();
                let formData = new FormData;

                formData.append("newBackgroundBannerSetting", JSON.stringify(new BackgroundBanner(null, $("#enabledBackgroundBanner")[0].checked)))

                if ($("#backgroundBanner")[0].value !== "") {
                    formData.append("newBackgroundBannersImage", $("#backgroundBanner")[0].files[0])
                }

                $.ajax(
                    {
                        type: 'POST',
                        url: "/admin/edit-background-banner",
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
        )
    }
)