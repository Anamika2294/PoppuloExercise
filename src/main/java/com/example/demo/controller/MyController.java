package com.example.demo.controller;

import com.example.demo.constants.Constant;
import com.example.demo.service.TicketServiceImpl;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MyController {
    @Autowired
    private TicketServiceImpl ticketService;

    @GetMapping(Constant.BASE_URL)
    public List<Ticket> getAllTickets() {
        List<Ticket> list=(List<Ticket>)ticketService.findAll();
        return list;
    }

    @GetMapping(Constant.BASE_URL+"/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") long id) {
        Optional<Ticket> ticketData = Optional.ofNullable(ticketService.get(id));

        if (ticketData.isPresent()) {
            return new ResponseEntity<>(ticketData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(Constant.BASE_URL)
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        try {
            ticketService.save(ticket);
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PutMapping(Constant.BASE_URL+"/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticket) {
        Optional<Ticket> ticketData = Optional.ofNullable(ticketService.get(id));

        if (ticketData.isPresent()) {
            Ticket _ticket = ticketData.get();
            _ticket.setTicket_no(ticket.getticket_no());
            System.out.println("Controller_ticket_no"+ticket.getticket_no());
            ticketService.save(_ticket);
            return new ResponseEntity<>(_ticket,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(Constant.BASE_URL_ALT+"/{id}")
    public ResponseEntity<?> getTicketStatus(@PathVariable("id") long id) {
        Optional<Ticket> ticketData = Optional.ofNullable(ticketService.get(id));
        Map<String,Object> hashStatus= new HashMap<>();
        if (ticketData.isPresent()) {
            hashStatus.put("Status",ticketData.get().getStatus());
            return new ResponseEntity<>(hashStatus,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
