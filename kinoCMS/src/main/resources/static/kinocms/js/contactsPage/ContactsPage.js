$(document).ready(
    function () {
        $.ajax(
            {
                type: 'POST',
                url: "/send-contact-information",
                contentType: false,
                cache: false,
                processData: false,
                success: function (contactsInformation) {
                    let contact = JSON.parse(contactsInformation.contact);
                    let contactsList = JSON.parse(contactsInformation.contactsList);

                    $("#contactsPageName").prepend(contact.name);

                    for (let contact of contactsList) {
                        contactToPage(contact.nameCinema, contact.address, contact.coordinatesForTheMap, contact.pathToLogo)
                    }

                    function contactToPage(nameCinema, address, coordinatesForTheMap, pathToLogo) {
                        $("#content").append(
                            $("<div>").attr("class", "row border border-dark mt-1").prepend(
                                $("<div>").attr("class", "col").prepend(
                                    $("<h3>").text(nameCinema),
                                    $("<img>").attr({
                                        class: "img-fluid",
                                        width: 300,
                                        src: pathToLogo,
                                        alt: "First slide"
                                    })
                                ),
                                $("<div>").attr("class", "col").prepend(
                                    $("<p>").text("Адрес:"),
                                    $("<p>").text(address),
                                    $("<iframe>").attr({
                                        width: "600",
                                        height: "450",
                                        style: "border:0",
                                        loading: "lazy",
                                        "allowfullscreen": true,
                                        src: "https://www.google.com/maps/embed/v1/place?key=AIzaSyCFRzLt6kb7sHCs4p-Sng4AeGKK3NGh68s&q="+ coordinatesForTheMap +"&zoom=18&maptype=satellite"
                                    })
                                )
                            )
                        )
                    }
                }
            }
        )
    }
)