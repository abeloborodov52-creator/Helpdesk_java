package helpdesk.repository;

import helpdesk.model.Ticket;

import java.util.*;

public class InMemoryTicketRepository implements TicketRepository{
    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public Ticket findById(long id){
        for (Ticket currentTicket : tickets) {
            if (currentTicket.getId() == id) {
                return currentTicket;
            }
        }
        return null;
    }

    @Override
    public List<Ticket> getAll(){
        return  tickets;
    }

    @Override
    public void save(Ticket ticket) {
        tickets.add(ticket);
    }

}
