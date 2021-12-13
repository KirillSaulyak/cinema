$(document).ready(
    function () {

        $.ajax(
            {
                type: 'POST',
                url: "/send-all-movies-information",
                contentType: JSON,
                cache: false,
                processData: false,
                success: function (moviesInformation) {
                    let movies = JSON.parse(moviesInformation.currentMovies)
                    let moviesTypes = JSON.parse(moviesInformation.currentMoviesTypes)
                    showAllMovies();
                    $("[name = 'userMoviesType']").change(
                        function () {
                            clean();
                            if ($("[name = 'userMoviesType']:checked").length === 0) {
                                showAllMovies();
                            } else {
                                moviesToTableByType($("[name = 'userMoviesType']:checked"));
                            }
                        }
                    )

                    function moviesToTableByType(chosenTypes) {
                        for (let m = 0; m < movies.length; m++) {
                            if (moviesTypes[m].length < chosenTypes.length) {
                                continue;
                            }
                            for (let t = 0; t < moviesTypes[m].length; t++) {
                                for (let chosenType of chosenTypes) {
                                    if (moviesTypes[m][t].type.includes($("[for = " + "\'" + chosenType.id + "\'" + "]")[0].innerText)
                                        && (t == moviesTypes[m].length - 1 || t == chosenTypes.length - 1)) {
                                        addMovieToTable(m, movies[m], moviesTypes[m]);
                                        break;
                                    }
                                }

                            }

                        }
                    }

                    function showAllMovies() {
                        for (let i = 0; i < movies.length; i++) {
                            addMovieToTable(i, movies[i], moviesTypes[i])
                        }
                    }

                    function clean() {
                        $("#movies").children().detach();
                    }

                    function addMovieToTable(id, movie, types) {
                        $("#movies").append(
                            $("<tr>").attr("id", "tableRow" + id).append(
                                $("<td>").prepend(
                                    $("<a>").attr("href", "/movie?idMovie=" + movie.idMovie).prepend(movie.name)
                                ),

                                $("<td>").prepend(
                                    function () {
                                        let allTypes = "";
                                        for (let type of types) {
                                            allTypes += " " + type.type;
                                        }
                                        return allTypes
                                    }
                                )
                            )
                        )
                    }
                }
            }
        )
    }
)