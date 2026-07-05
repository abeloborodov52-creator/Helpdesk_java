package helpdesk.service;

import helpdesk.model.Comment;
import helpdesk.model.Ticket;
import helpdesk.model.TicketStatus;
import helpdesk.model.User;

import java.time.LocalDateTime;
import java.util.*;

public class InMemoryTicketService
        implements TicketService {

    private List<Ticket> tickets = new ArrayList<>();
    private long nextId = 1;
    private long nextCommentId = 0;

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

    public Comment addComment(long ticketId,
                              String text,
                              User author){
        Ticket foundTicket = findById(ticketId);
        if (foundTicket == null) {
            return null;
        }
        Comment comment = new Comment(nextCommentId++, text, author, LocalDateTime.now());
        foundTicket.addComment(comment);
        return comment;

    }
    public List<Comment> getComments(long ticketId){
        Ticket foundTicket = findById(ticketId);
        if (foundTicket == null) {
            return Collections.emptyList();
        }
        return foundTicket.getComments();
    };

    public List<Ticket> findTicketsByPerson(long userId) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getAuthor() == null) {
                return null;
            }
            if (ticket.getAuthor().getId() == userId) {
                result.add(ticket);
            }
        }
        return result;
    }
    public void setAssignee(long ticketId, User user) {
        findById(ticketId).setAssignee(user);
    }
    public boolean removeAssignee(long ticketId) {
        findById(ticketId).setAssignee(null);
        return true;
    }



}
