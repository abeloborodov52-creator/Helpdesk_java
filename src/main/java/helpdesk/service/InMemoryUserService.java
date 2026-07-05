package helpdesk.service;

import helpdesk.model.Ticket;
import helpdesk.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserService
        implements UserService {
    private List<User> users = new ArrayList<>();
    private long nextId = 1;


    @Override
    public User createUser(String username) {
        User user = new User(nextId, username);
        users.add(user);
        nextId++;
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User findUserById(long id) {
        for (User currentUser : users) {
            if (currentUser.getId() == id) {
                return currentUser;
            }
        }
        return null;
    }






}
