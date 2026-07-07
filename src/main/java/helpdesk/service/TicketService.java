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

    Ticket changeStatus(long id, TicketStatus status);

    Comment addComment(long ticketId,
                              String text,
                              User author);
    List<Comment> getComments(long ticketId);

    List<Ticket> findTicketsByAuthor(long userId);

    boolean setAssignee(long ticketId, User user);

    boolean removeAssignee(long ticketId);
}
