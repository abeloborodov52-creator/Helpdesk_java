package helpdesk.service;

import helpdesk.model.Ticket;
import helpdesk.model.User;
import java.util.List;

public interface UserService {

    User createUser(String username);

    List<User> getAllUsers();

    User findUserById(long id);





}
