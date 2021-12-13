package com.kinocms.cinema.сontrollers.kinocms.page.chilldrensRoomPage;

import com.kinocms.cinema.model.ChildrensRoomsEntity;
import com.kinocms.cinema.model.ChildrensRoomsGalleriesEntity;
import com.kinocms.cinema.services.ChildrensRoomsGalleriesService;
import com.kinocms.cinema.services.ChildrensRoomsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserChilldrensRoomPageRestController {
    @Autowired
    private ChildrensRoomsService chilldrensRoomsService;
    @Autowired
    private ChildrensRoomsGalleriesService chilldrensRoomsGalleriesService;

    @PostMapping("/send-chilldrens-room-information")
    public Map<String, String> sendChosenCafeInformation() {
        Gson gson = new Gson();
        Map<String, String> chilldrensRoomInformation = new HashMap<>();

        ChildrensRoomsEntity chilldrensRoomsEntity = chilldrensRoomsService.findFirst();
        chilldrensRoomInformation.put("chilldrensRoom", gson.toJson(chilldrensRoomsEntity));

        List<ChildrensRoomsGalleriesEntity> chilldrensRoomsGallery = chilldrensRoomsGalleriesService.findByChildrensRoomsByChildrensRoomFk(chilldrensRoomsEntity);
        chilldrensRoomInformation.put("chilldrensRoomsGallery", gson.toJson(chilldrensRoomsGallery));

        return chilldrensRoomInformation;
    }
}
