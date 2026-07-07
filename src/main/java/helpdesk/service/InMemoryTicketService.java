package helpdesk.service;

import helpdesk.model.Comment;
import helpdesk.model.Ticket;
import helpdesk.model.TicketStatus;
import helpdesk.model.User;
import helpdesk.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.*;

public class InMemoryTicketService
        implements TicketService {

    private final TicketRepository repository;
    private long nextId = 1;
    private long nextCommentId = 1;

    public InMemoryTicketService(TicketRepository repository) {
        this.repository = repository;
    }
    @Override
    public Ticket createTicket(String title, String description, User author) {
        Ticket ticket = new Ticket(nextId, title, description, TicketStatus.OPEN, author);
        repository.save(ticket);
        nextId++;
        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return repository.getAll();
    }

    @Override
    public Ticket findById(long id) {
        return repository.findById(id);
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

    public List<Ticket> findTicketsByAuthor(long userId) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : repository.getAll()) {
            if (ticket.getAuthor() != null && (ticket.getAuthor().getId() == userId)) {
                result.add(ticket);
            }
        }
        return result;
    }
    public boolean setAssignee(long ticketId, User user) {
        Ticket ticket = findById(ticketId);

        if (ticket == null || user == null) {
            return false;
        }

        ticket.setAssignee(user);

        return true;
    }
    public boolean removeAssignee(long ticketId) {
        Ticket ticket = findById(ticketId);
        if (ticket == null) {
            return false;
        }

        ticket.setAssignee(null);

        return true;
    }



}
