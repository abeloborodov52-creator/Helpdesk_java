package helpdesk.repository;

import helpdesk.model.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> getAllUsers();

    User findById(long id);


}
