package com.kinocms.cinema.сontrollers.kinocms.page.chilldrensRoomPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserChilldrensRoomPageController {
    @GetMapping("/chilldrens-room")
    public String showChilldrensRoom()
    {
        return "kinocms/chilldrensRoomPage/ChilldrensRoomPage";
    }
}
