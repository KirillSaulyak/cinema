$(document).ready(
    function () {
        $("#NavBarVertical li a").click(
            function () {
                $("#NavBarVertical li a.active").removeClass("active")
                $(this).addClass("active")
            }
        )
    }
)
