package com.example.controller;

import com.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TicketController {


    @Autowired
    TicketService ticketService;

    @GetMapping("/hello")
    public String getTicket(){
        return ticketService.getTicket();
    }
}
