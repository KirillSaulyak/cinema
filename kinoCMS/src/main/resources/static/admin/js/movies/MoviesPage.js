$(document).ready(
    function () {
        let hideState = "HiddenMoviesList";
        let currentState = "CurrentMoviesList";
        let futureState = "FutureMoviesList";
        if (pathListImagesHiddenMovies.length > 1) {
            pathListImagesHiddenMovies = pathListImagesHiddenMovies.replace(/[\[\]]/g, "").split(",");
            nameHiddenMovies = nameHiddenMovies.replace(/[\[\]]/g, "").split(",");
            for (let i = 0; i < pathListImagesHiddenMovies.length; i++) {
                createMovieInterfaceSetting(nameHiddenMovies[i].trim(), hideState, pathListImagesHiddenMovies[i].trim(), idHiddenMovies[i].trim());
            }
        }

        if (pathListImagesCurrentMovies.length > 1) {
            pathListImagesCurrentMovies = pathListImagesCurrentMovies.replace(/[\[\]]/g, "").split(",");
            nameCurrentMovies = nameCurrentMovies.replace(/[\[\]]/g, "").split(",");
            for (let i = 0; i < pathListImagesCurrentMovies.length; i++) {
                createMovieInterfaceSetting(nameCurrentMovies[i].trim(), currentState, pathListImagesCurrentMovies[i].trim(), idCurrentMovies[i].trim());
            }
        }

        if (pathListImagesFutureMovies.length > 1) {
            pathListImagesFutureMovies = pathListImagesFutureMovies.replace(/[\[\]]/g, "").split(",");
            nameFutureMovies = nameFutureMovies.replace(/[\[\]]/g, "").split(",");
            for (let i = 0; i < pathListImagesFutureMovies.length; i++) {
                createMovieInterfaceSetting(nameFutureMovies[i].trim(), futureState, pathListImagesFutureMovies[i].trim(), idFutureMovies[i].trim());
            }
        }

        function createMovieInterfaceSetting(name, moviesState, path, id) {
            $("#" + moviesState).prepend(
                $("<div>").attr("class", "col-auto my-3").prepend(
                    $("<div>").attr("class", "row d-flex justify-content-center").prepend(
                        $("<div>").attr("class", "col dropdown").prepend(
                            $("<button>").attr({
                                class: "btn btn-secondary dropdown-toggle",
                                type: "button",
                                id: "dropdownMenu1",
                                "data-toggle": "dropdown",
                                "aria-haspopup": true,
                                "aria-expanded": false
                            }).text("Состояние фильма"),
                            $("<div>").attr({
                                class: "dropdown-menu",
                                "aria-labelledby": "dropdownMenu1"
                            }).append(
                                $("<button>").attr({
                                    class: "dropdown-item",
                                    type: "button"
                                }).text("Скрытый").click(function () {
                                    changeMoviesState(id, moviesState, "HiddenMoviesList")
                                }),
                                $("<button>").attr({
                                    class: "dropdown-item",
                                    type: "button"
                                }).text("Сейчас показывают").click(function () {
                                    changeMoviesState(id, moviesState, "CurrentMoviesList")
                                }),
                                $("<button>").attr({
                                    class: "dropdown-item",
                                    type: "button"
                                }).text("Скоро покажут").click(function () {
                                    changeMoviesState(id, moviesState, "FutureMoviesList")
                                })
                            )
                        )
                    ),
                    $("<div>").attr("class", "row").prepend(
                        $("<div>").attr("class", "col d-flex justify-content-center").prepend(
                            $("<p>").text(name)
                        )
                    ),
                    $("<div>").attr("class", "row").prepend(
                        $("<div>").attr("class", "col d-flex justify-content-center").prepend(
                            $("<img>").attr({
                                class: "img-thumbnail my-2",
                                style: "height: 120px",
                                src: path,
                                id: "Movie" + id,
                            })
                        )
                    ),
                    $("<div>").attr("class", "row").prepend(
                        $("<div>").attr("class", "col d-flex justify-content-center").prepend(
                            $("<button>").attr({
                                type: "submit",
                                class: "btn btn-info mr-1",
                                name: "change",
                                id: id
                            }).text("Изменить").click(
                                function (e) {
                                    e.preventDefault();
                                    changeMovie(this.id)
                                }
                            ),
                            $("<button>").attr({
                                type: "button",
                                class: "btn btn-danger",
                                name: "delete",
                                id: id
                            }).text("Удалить").click(
                                function (e) {
                                    e.preventDefault();
                                    deleteMovie(this.id)
                                }
                            )
                        )
                    )
                )
            )
        }

        function changeMovie(id) {
            window.location = "/admin/movies-change?moviesId=" + id;
        }

        function changeMoviesState(moviesId, moviesOldState, moviesNewState) {
            let formData = new FormData();
            formData.append("moviesId", moviesId);
            formData.append("moviesOldState", moviesOldState);
            formData.append("moviesNewState", moviesNewState);
            $.ajax(
                {
                    type: 'POST',
                    url: "/admin/change-movies-state",
                    data: formData,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function () {
                        window.location = "/admin/movies"
                    },
                    error: function () {
                        alert("Не удалось сменить состояние")
                    }
                }
            )
        }

        function deleteMovie(id) {
            let formData = new FormData();
            formData.append("moviesId", id);
            $.ajax(
                {
                    type: 'DELETE',
                    url: "/admin/movies-delete",
                    data: formData,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function () {
                        window.location = "/admin/movies"
                    },
                    error: function () {
                        alert("Удалить не удалось")
                    }
                }
            )
        }
    }
)