package com.example.demo.model;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "tickets")
public class Ticket implements Comparable<Ticket> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTicket_no(String ticket_no) {
        this.ticket_no = ticket_no;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String ticket_no;
    private int status;

    public Ticket(Long id, String ticket_no, int status) {
        this.id = id;
        this.ticket_no = ticket_no;
        this.status = status;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public String getticket_no() {
        return ticket_no;
    }


    public int getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticket_no=" + ticket_no +
                ", status=" + status +
                '}';
    }


    @Override
    public int compareTo(Ticket ticket) {
        int compareTicket=Integer.parseInt(ticket.ticket_no);
        String stringCurrentTicket= this.ticket_no;
        int currentTicket= Integer.parseInt(stringCurrentTicket);
        return currentTicket-compareTicket;
    }
}
