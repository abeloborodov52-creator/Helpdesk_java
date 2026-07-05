package helpdesk.model;


import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private User author;
    private List<Comment> comments;

    public Ticket(Long id, String title, String description, TicketStatus status, User author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.author = author;
        comments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status + '\'' +
                ", author=" + author +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public void addComment(Comment comment) {

        comments.add(comment);
    }
}
