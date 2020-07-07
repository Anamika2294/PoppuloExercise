package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private TicketRepository repository;

    @Override
    public List<Ticket> findAll() {

        List<Ticket> list= repository.findAll();
        Collections.sort(list);

        return list;
    }

    @Override
    public Ticket get(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void save(Ticket ticket) {
          int[] result = Arrays.stream(ticket.getticket_no().split("")).mapToInt(Integer::parseInt).toArray();

        if(result[0] == result[1] && result[1] == result[2] && result[0] == result[2]){
            ticket.setStatus(5);
        }
        else if(result[0] != result[1] && result[1] == result[2]){
            ticket.setStatus(1);
        }
        else if(result[0]+result[1]+result[2]==2){
            ticket.setStatus(10);
        }
        else{
            ticket.setStatus(0);
        }
        repository.save(ticket);
    }




}
