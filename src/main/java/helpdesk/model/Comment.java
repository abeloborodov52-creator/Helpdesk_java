package helpdesk.model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;

    private String text;

    private User author;

    private LocalDateTime createdAt;

    public Comment(Long id, String text, User author, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return """
Автор: %s
Дата: %s
Комментарий:
%s
""".formatted(
                author.getUsername(),
                createdAt,
                text
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
