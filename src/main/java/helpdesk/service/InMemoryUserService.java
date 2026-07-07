package helpdesk.service;

import helpdesk.model.Ticket;
import helpdesk.model.User;
import helpdesk.repository.TicketRepository;
import helpdesk.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserService
        implements UserService {

    private final UserRepository repository;
    private long nextId = 1;

    public InMemoryUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(String username) {
        User user = new User(nextId, username);
        repository.save(user);
        nextId++;
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public User findUserById(long id) {
        return repository.findById(id);
    }






}
