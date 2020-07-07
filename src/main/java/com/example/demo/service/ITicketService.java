package com.example.demo.service;

import com.example.demo.model.Ticket;

import java.util.List;

interface  ITicketService {
    List<Ticket> findAll();
    Ticket get(Long id);
    void save(Ticket ticket);

}
