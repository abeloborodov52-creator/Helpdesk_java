package helpdesk.service;

import helpdesk.model.Ticket;
import helpdesk.model.User;
import java.util.List;

public interface UserService {

    User createUser(String username);


    User findUserById(long id);

    List<User> getAllUsers();



}
