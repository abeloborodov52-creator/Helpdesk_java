package helpdesk.repository;

import helpdesk.model.Ticket;

import java.util.List;

public interface TicketRepository {

    void save(Ticket ticket);

    List<Ticket> getAll();

    Ticket findById(long id);

}
