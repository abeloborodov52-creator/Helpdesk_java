package helpdesk.repository;

import helpdesk.model.Ticket;
import helpdesk.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository{
    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User findById(long id) {
        for (User currentUser : users) {
            if (currentUser.getId() == id) {
                return currentUser;
            }
        }
        return null;
    }
    public void save(User user) {
        users.add(user);
    }
}
