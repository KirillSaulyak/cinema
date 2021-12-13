$(document).ready(
    function () {
        class NewCinema {
            idCinema;
            name;
            description;
            conditions;
            pathToLogo;
            pathToTopBanner;

            constructor(idCinema, name, description, conditions, pathToLogo, pathToTopBanner) {
                this.idCinema = idCinema;
                this.name = name;
                this.description = description;
                this.conditions = conditions;
                this.pathToLogo = pathToLogo;
                this.pathToTopBanner = pathToTopBanner;
            }

            get idCinema() {
                return this.idCinema;
            }

            set idCinema(value) {
                this.idCinema = value;
            }

            get name() {
                return this.name;
            }

            set name(value) {
                this.name = value;
            }

            get description() {
                return this.description;
            }

            set description(value) {
                this.description = value;
            }

            get conditions() {
                return this.conditions;
            }

            set conditions(value) {
                this.conditions = value;
            }

            get pathToLogo() {
                return this.pathToLogo;
            }

            set pathToLogo(value) {
                this.pathToLogo = value;
            }

            get pathToTopBanner() {
                return this.pathToTopBanner;
            }

            set pathToTopBanner(value) {
                this.pathToTopBanner = value;
            }
        }

        let newCinema = new NewCinema();
        let cinemasImagesGallery = new Map;


        let mapOldCinemasImagesGalleryId = new Map;
        let deleteOldCinemasImagesFromGalley = [];
        let countGalleriesImages = 0;

        class NewHall {
            name;
            description;
            dateCreated


            constructor(name, description, dateCreated) {
                this.name = name;
                this.description = description;
                this.dateCreated = dateCreated;
            }

            get name() {
                return this.name;
            }

            set name(value) {
                this.name = value;
            }

            get description() {
                return this.description;
            }

            set description(value) {
                this.description = value;
            }

            get dateCreated() {
                return this.dateCreated;
            }

            set dateCreated(value) {
                this.dateCreated = value;
            }
        }

        let hall = new NewHall();
        let mapOldHalls = new Map;
        let countHallsInTable = 0;
        let mapHallsImagesGallery = new Map;
        let mapOldHallsImagesGallery = new Map;
        let idDeleteOldHallsImagesFromGallery = [];
        let countHallsGalleriesImages = 0;
        let idHall = 0;
        let currentTable = 0;
        if (cinema != "") {
            cinema = JSON.parse(cinema.replace(/&quot;/g, '"'));
            newCinema.idCinema = cinema.idCinema
            $("#cinemasName")[0].value = newCinema.name = cinema.name;
            $("#cinemasDescription")[0].value = newCinema.description = cinema.description;
            $("#cinemasСonditions")[0].value = newCinema.conditions = cinema.conditions;
            $("#cinemasLogoImage").attr("src", newCinema.pathToLogo = cinema.pathToLogo);
            $("#cinemasTopBannerImage").attr("src", newCinema.pathToTopBanner = cinema.pathToTopBanner);
            cinemasImagesFromGallery = JSON.parse(cinemasImagesFromGallery.replace(/&quot;/g, '"'));
            for (let image of cinemasImagesFromGallery) {
                mapOldCinemasImagesGalleryId.set(countGalleriesImages.toString(), image.idCinemasImage)
                addImageToGallery(image.pathToImage)
            }

            if (halls != "") {
                halls = JSON.parse(halls.replace(/&quot;/g, '"'));
                hallsImagesFromGallery = JSON.parse(hallsImagesFromGallery.replace(/&quot;/g, '"'));
                identificationHalls()
                reloadHallsInTable()
            }

            $("#hallsTool").attr("class", "");

            function identificationHalls() {
                for (let i = 0; i < halls.length; i++) {
                    mapOldHalls.set(i.toString(), halls[i]);
                }
            }

            function reloadHallsInTable() {
                for (let hall of halls) {
                    addHallToTable(countHallsInTable.toString(), hall)
                    countHallsInTable++;
                }
            }

            function addHallToTable(id, hallToTable) {
                $("#entryIntoTheHallsTable").append(
                    $("<tr>").attr("id", "tableRow" + id).append(
                        $("<td>").text(hallToTable.name),
                        $("<td>").text(hallToTable.creationDate),
                        $("<td>").append(
                            $("<button>").attr({
                                type: "button",
                                class: "btn btn-primary mr-2",
                                "data-toggle": "modal",
                                "data-target": "#hallsModalWindow",
                                id: id
                            }).text("Изменить").click(
                                function (e) {
                                    e.preventDefault();
                                    resetHallsWindow();
                                    identificationHalls();
                                    reloadHallsInTable();
                                    currentTable = this.id
                                    idHall = mapOldHalls.get(this.id).idHall;
                                    $("#hallsName")[0].value = hall.name = mapOldHalls.get(this.id).name
                                    $("#hallsDescription")[0].value = hall.description = mapOldHalls.get(this.id).description
                                    $("#hallsLayoutImage").attr("src", mapOldHalls.get(this.id).pathToHallLayout)
                                    $("#hallsTopBannerImage").attr("src", mapOldHalls.get(this.id).pathToTopBanner)
                                    hall.dateCreated = halls[this.id].dateCreated;
                                    for (let imagesScr of hallsImagesFromGallery[this.id]) {
                                        mapOldHallsImagesGallery.set(countHallsGalleriesImages.toString(), imagesScr.idHallsGallery)
                                        addImageToHallsGallery(imagesScr.pathToImage)
                                    }

                                }
                            ),
                            $("<button>").attr({
                                type: "button",
                                class: "btn btn-danger",
                                name: id
                            }).text("Удалить").click(
                                function (e) {
                                    e.preventDefault();
                                    $("#tableRow" + this.name).detach();
                                    let formData = new FormData;
                                    formData.append("idDeleteHall", halls[this.name].idHall);
                                    halls.splice(this.name, 1);
                                    $.ajax(
                                        {
                                            type: 'DELETE',
                                            url: "/admin/delete-hall",
                                            data: formData,
                                            contentType: false,
                                            cache: false,
                                            processData: false,
                                            success: function () {
                                                alert("Удаление прошло успешно")
                                            },
                                            error: function () {
                                                alert("Сохранить не удалось")
                                            }
                                        }
                                    )
                                }
                            )
                        )
                    )
                )
            }

            $("#addCinemasHall").click(
                function (e) {
                    e.preventDefault();
                    resetHallsWindow();
                }
            )
        }

        $('#hallsModalWindow').on('hidden.bs.modal', function () {
            resetHallsWindow();
            identificationHalls();
            reloadHallsInTable();
        })


        function resetHallsWindow() {
            for (let i = countHallsInTable; i >= 0; i--) {
                $("#tableRow" + i).detach();
            }
            countHallsInTable = 0;
            idHall = 0;
            countHallsGalleriesImages = 0;
            mapOldHalls.clear();
            mapHallsImagesGallery.clear();
            mapOldHallsImagesGallery.clear();
            idDeleteOldHallsImagesFromGallery.splice(0);
            mapHallsImagesGallery.clear();
            mapOldHallsImagesGallery.clear();
            hall = new NewHall();
            $("#modalItems")[0].reset();
            $("[name = 'mainImages']").attr("src", "images/emptyImg.png")
            $("[name ='hallsImagesToGallery']").detach();
            while ($("#entryIntoTheHallsTable").firstChild) {
                $("#entryIntoTheHallsTable").removeChild($("#entryIntoTheHallsTable").firstChild);
            }
        }


        function addImageToHallsGallery(src) {

            $("#hallsImagesGallery").before(
                $("<div>").attr({
                    class: "col-4 mt-2",
                    id: "hallsImageToGallery" + countHallsGalleriesImages,
                    name: "hallsImagesToGallery"
                }).prepend(
                    $("<img>").attr({
                        class: "img-thumbnail",
                        style: "height: 120px; max-width: 160px",
                        src: src,
                        alt: "default or upload img"
                    }),

                    $("<img>").attr({
                        class: "img-fluid align-top mr-2",
                        src: "images/close.svg",
                        alt: "close",
                        id: countHallsGalleriesImages
                    }).click(function (e) {
                        e.preventDefault();
                        if (!mapHallsImagesGallery.delete(this.id)) {
                            idDeleteOldHallsImagesFromGallery.push(mapOldHallsImagesGallery.get(this.id));
                            hallsImagesFromGallery[currentTable].splice(this.id, 1)
                        }
                        $("#hallsImageToGallery" + this.id).detach();
                    }),
                )
            )
            countHallsGalleriesImages++;
        }


        $("#hallsName").change(
            function () {
                hall.name = $("#hallsName")[0].value;
            }
        )

        $("#hallsDescription").change(
            function () {
                hall.description = this.value;
            }
        )
        $("#uploadHallsLayoutImage").change(
            function () {
                hall.hallsLayoutImage = this.files[0];
                let reader = new FileReader();
                reader.onload = function () {
                    $("#hallsLayoutImage").attr("src", reader.result)
                }
                reader.readAsDataURL(this.files[0]);
            }
        )
        $("#uploadHallsTopBannerImage").change(
            function () {
                hall.hallsTopBannerImage = this.files[0];
                let reader = new FileReader();
                reader.onload = function () {
                    $("#hallsTopBannerImage").attr("src", reader.result)
                }
                reader.readAsDataURL(this.files[0]);
            }
        )

        $("#addToHallsGalleryImage").change(
            function () {
                mapHallsImagesGallery.set(countHallsGalleriesImages.toString(),
                    $("#addToHallsGalleryImage")[0].files[0]);
                let reader = new FileReader();
                reader.onload = function () {
                    addImageToHallsGallery(reader.result)
                }
                reader.readAsDataURL(this.files[0]);
            }
        )

        $("#saveHall").click(
            function (e) {
                e.preventDefault();
                if (!$("#hallsName")[0].validity.valueMissing && !$("#hallsDescription")[0].validity.valueMissing) {
                    let formData = new FormData;
                    formData.append("cinemaId", newCinema.idCinema);
                    formData.append("idHall", idHall);
                    formData.append("hallsInformation", JSON.stringify(hall));
                    formData.append("hallsLayot", $("#uploadHallsLayoutImage")[0].files[0]);
                    formData.append("hallsTopBanner", $("#uploadHallsTopBannerImage")[0].files[0]);
                    for (let image of mapHallsImagesGallery.values())
                        formData.append("hallsGallery", image);
                    for (let id of idDeleteOldHallsImagesFromGallery.values()) {
                        formData.append("idDeleteOldImagesFromGalley", id);
                    }
                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/edit-hall",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function (newHallsAndGalleries) {
                                alert("Сохранение прошло успешно")
                                $('#hallsModalWindow').modal('hide')
                                resetHallsWindow();
                                let newHall = JSON.parse(newHallsAndGalleries["Hals"].replace(/&quot;/g, '"'));
                                let newHallsGallery = JSON.parse(newHallsAndGalleries["HallsGalleries"].replace(/&quot;/g, '"'));
                                let updateHall = false;
                                for (let i = 0; i < halls.length; i++) {//let hall of halls) {
                                    if (halls[i].idHall === newHall.idHall) {
                                        halls[i] = newHall;
                                        updateHall = true;
                                        break;
                                    }
                                }
                                if (!updateHall) {
                                    halls.push(newHall);
                                }
                                if (newHallsGallery.length != 0) {

                                    let updatedHallsGallery = false;
                                    let countUpdated = 0;
                                    for (let imagesFromGallery of hallsImagesFromGallery) {
                                        if (updatedHallsGallery) {
                                            break;
                                        }
                                        for (let i = 0; i <= imagesFromGallery.length; i++) {
                                            if (updatedHallsGallery) {
                                                break;
                                            }
                                            for (let newImage of newHallsGallery) {
                                                if (imagesFromGallery.length === i) {
                                                    if (imagesFromGallery[i - 1].hallsByHallFk.idHall === newImage.hallsByHallFk.idHall) {
                                                        imagesFromGallery.push(newImage)
                                                        updatedHallsGallery = true;
                                                    }
                                                    break;
                                                }
                                                if (imagesFromGallery[i].hallsByHallFk.idHall === newImage.hallsByHallFk.idHall) {
                                                    if (imagesFromGallery[i].idHallsGallery === newImage.idHallsGallery) {
                                                        imagesFromGallery[i] = newImage.idHallsGallery;
                                                        countUpdated++;
                                                        if (countUpdated === newHallsGallery.length) {
                                                            updatedHallsGallery = true;
                                                        }
                                                    }
                                                } else {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (!updatedHallsGallery) {
                                        hallsImagesFromGallery.push(newHallsGallery);
                                    }
                                }
                                identificationHalls();
                                reloadHallsInTable();
                            },
                            error: function () {
                                alert("Сохранить не удалось")
                            }
                        }
                    )
                }
            }
        )


        $("#cinemasName").change(
            function () {
                newCinema.name = this.value
            }
        )

        $("#cinemasDescription").change(
            function () {
                newCinema.description = this.value;
            }
        )

        $("#cinemasСonditions").change(
            function () {
                newCinema.conditions = this.value;
            }
        )

        $("#uploadCinemasLogoImage").change(
            function () {
                let reader = new FileReader();
                reader.onload = () => $("#cinemasLogoImage").attr("src", reader.result);
                reader.readAsDataURL(this.files[0]);
            }
        )

        $("#uploadCinemasTopBannerImage").change(
            function () {
                let reader = new FileReader();
                reader.onload = () => $("#cinemasTopBannerImage").attr("src", reader.result);
                reader.readAsDataURL(this.files[0]);
            }
        )

        $("#addToCinemasGalleryImages").change(
            function () {
                cinemasImagesGallery.set(countGalleriesImages.toString(),
                    $("#addToCinemasGalleryImages")[0].files[0]);
                let reader = new FileReader();
                reader.onload = function () {
                    addImageToGallery(reader.result, countGalleriesImages);
                }
                reader.readAsDataURL(this.files[0]);
            }
        )

        function addImageToGallery(src) {
            $("#cinemasImagesGallery").before(
                $("<div>").attr({class: "mt-2 mx-2", id: "cinemasImageToGallery" + countGalleriesImages,}).prepend(
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
                        if (!cinemasImagesGallery.delete(this.id)) {
                            deleteOldCinemasImagesFromGalley.push(mapOldCinemasImagesGalleryId.get(this.id));
                        }
                        $("#cinemasImageToGallery" + this.id).detach();
                    }),
                )
            )
            countGalleriesImages++;
        }

        $("#saveCinema").click(
            function (e) {
                e.preventDefault();
                if (!$("#cinemasName")[0].validity.valueMissing &&
                    !$("#cinemasDescription")[0].validity.valueMissing &&
                    !$("#cinemasСonditions")[0].validity.valueMissing) {
                    let formData = new FormData;
                    formData.append("cinemasInformation", JSON.stringify(newCinema));
                    formData.append("cinemasLogo", $("#uploadCinemasLogoImage")[0].files[0]);
                    formData.append("cinemasTopBanner", $("#uploadCinemasTopBannerImage")[0].files[0]);
                    for (let image of cinemasImagesGallery.values()) {
                        formData.append("cinemasGallery", image);
                    }
                    for (let id of deleteOldCinemasImagesFromGalley) {
                        formData.append("deleteOldImagesFromGalley", id)
                    }
                    $.ajax(
                        {
                            type: 'POST',
                            url: "/admin/edit-cinema",
                            data: formData,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success: function () {
                                alert("Сохранение прошло успешно")
                                window.location = "/admin/cinemas"
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