import helpdesk.service.InMemoryTicketService;
import helpdesk.service.InMemoryUserService;
import helpdesk.service.TicketService;
import helpdesk.service.UserService;
import helpdesk.ui.ConsoleUi;

import java.util.*;

void main() {

    TicketService ticketService = new InMemoryTicketService();
    UserService userService = new InMemoryUserService();
    ConsoleUi consoleUi = new ConsoleUi(ticketService, userService);
    consoleUi.start();

}




