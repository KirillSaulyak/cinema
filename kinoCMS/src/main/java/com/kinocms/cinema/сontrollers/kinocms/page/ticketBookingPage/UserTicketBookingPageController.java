package com.kinocms.cinema.сontrollers.kinocms.page.ticketBookingPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserTicketBookingPageController {
    @GetMapping("/ticket-booking")
    public String showTicketBookingPage()
    {
        return "kinocms/ticketBookingPage/TicketBookingPage";
    }
}
