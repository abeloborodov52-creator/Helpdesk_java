package helpdesk.service;

import helpdesk.model.Ticket;
import helpdesk.model.TicketStatus;
import helpdesk.model.User;

import java.util.*;

public class InMemoryTicketService
        implements TicketService {

    private List<Ticket> tickets = new ArrayList<>();
    private long nextId = 1;

    @Override
    public Ticket createTicket(String title, String description, User author) {
        Ticket ticket = new Ticket(nextId, title, description, TicketStatus.OPEN, author);
        tickets.add(ticket);
        nextId++;
        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return tickets;
    }

    @Override
    public Ticket findById(long id) {
        for (Ticket currentTicket : tickets) {
            if (currentTicket.getId() == id) {
                return currentTicket;
            }
        }
        return null;
    }
    public Ticket changeStatus(long id, TicketStatus status){
        Ticket foundTicket = findById(id);
        if (foundTicket == null) {
            return null;
        }
        if (status == null) {
            return null;
        }
        foundTicket.setStatus(status);
        return foundTicket;
    }
}
