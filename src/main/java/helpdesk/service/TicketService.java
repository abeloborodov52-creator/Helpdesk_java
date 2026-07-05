package helpdesk.service;

import helpdesk.model.Comment;
import helpdesk.model.Ticket;
import helpdesk.model.TicketStatus;
import helpdesk.model.User;

import java.util.*;

public interface TicketService {
    Ticket createTicket(String title, String description, User author);

    List<Ticket> getAllTickets();

    Ticket findById(long id);

    public Ticket changeStatus(long id, TicketStatus status);

    public Comment addComment(long ticketId,
                              String text,
                              User author);
    public List<Comment> getComments(long ticketId);

    public List<Ticket> findTicketsByPerson(long userId);

    public void setAssignee(long ticketId, User user);

    public boolean removeAssignee(long ticketId);
}
