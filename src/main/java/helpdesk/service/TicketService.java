package helpdesk.service;

import helpdesk.model.Ticket;
import helpdesk.model.TicketStatus;
import helpdesk.model.User;

import java.util.*;

public interface TicketService {
    Ticket createTicket(String title, String description, User author);

    List<Ticket> getAllTickets();

    Ticket findById(long id);

    public Ticket changeStatus(long id, TicketStatus status);
}
