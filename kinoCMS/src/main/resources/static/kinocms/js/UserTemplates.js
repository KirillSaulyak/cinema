$(document).ready(
    function () {

        $.ajax(
            {
                type: 'POST',
                url: "/send-state-default-pages",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (stateDefaultPages) {
                    let descriptionCurrentCinema = JSON.parse(stateDefaultPages.descriptionCurrentCinema)
                    if (!descriptionCurrentCinema) {
                        $("[name = linkAboutCinema]").attr("hidden", "true")
                    }

                    let cafe = JSON.parse(stateDefaultPages.cafe)
                    if (!cafe) {
                        $("[name = linkCafe]").attr("hidden", "true")
                    }

                    let vipHall = JSON.parse(stateDefaultPages.vipHall)
                    if (!vipHall) {
                        $("[name = linkVipHall]").attr("hidden", "true")
                    }

                    let advertisement = JSON.parse(stateDefaultPages.advertisement)
                    if (!advertisement) {
                        $("[name = linkAdvertisement]").attr("hidden", "true")
                    }

                    let childrensRoom = JSON.parse(stateDefaultPages.childrensRoom)
                    if (!childrensRoom) {
                        $("[name = linkСhilldrensRoom]").attr("hidden", "true")
                    }

                    let contact = JSON.parse(stateDefaultPages.contact)
                    if (!contact) {
                        $("[name = linkContacts]").attr("hidden", "true")
                    }

                }
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/send-additional-pages",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (additionalPagesInformation) {
                    let additionalPages = JSON.parse(additionalPagesInformation.additionalPages)
                    for (let i = 0; i < additionalPages.length; i++) {
                        if (additionalPages[i].enabled) {
                            $("#navbarDropdown").append(
                                $("<a>").attr({
                                    class: "dropdown-item",
                                    href: ("additional-page?idAdditionalPage=" + additionalPages[i].idAdditionalPage)
                                }).text(additionalPages[i].name)
                            )
                        }
                    }
                }
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/send-background-banner",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (backgroundBanner) {
                    backgroundBanner = JSON.parse(backgroundBanner);
                    if (backgroundBanner.enabled) {
                        $("#userBody").attr({
                            class: " ",
                            "background": backgroundBanner.pathToBackgroundBanner
                        })
                    }
                }
            }
        )

        $.ajax(
            {
                type: 'POST',
                url: "/send-phone-number",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (mainPageInformation) {
                    mainPageInformation = JSON.parse(mainPageInformation);
                    if (mainPageInformation.enabled) {
                        $("#userPhoneNumber").prepend(
                            $("<p>").attr("class", "bg-light font-weight-bolder").text(mainPageInformation.firstPhone),
                            $("<p>").attr("class", "bg-light font-weight-bolder").text(mainPageInformation.secondPhone)
                        )
                    }
                }
            }
        )
    }
)