import helpdesk.repository.InMemoryTicketRepository;
import helpdesk.repository.InMemoryUserRepository;
import helpdesk.repository.TicketRepository;
import helpdesk.repository.UserRepository;
import helpdesk.service.InMemoryTicketService;
import helpdesk.service.InMemoryUserService;
import helpdesk.service.TicketService;
import helpdesk.service.UserService;
import helpdesk.ui.ConsoleUi;

import java.util.*;

void main() {
    TicketRepository ticketRepository = new InMemoryTicketRepository();
    TicketService ticketService = new InMemoryTicketService(ticketRepository);
    UserRepository userRepository = new InMemoryUserRepository();
    UserService userService = new InMemoryUserService(userRepository);
    ConsoleUi consoleUi = new ConsoleUi(ticketService, userService);
    consoleUi.start();

}




