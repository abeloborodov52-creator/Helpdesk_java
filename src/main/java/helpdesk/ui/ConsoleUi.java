package helpdesk.ui;
import helpdesk.model.Comment;
import helpdesk.model.Ticket;
import helpdesk.model.TicketStatus;
import helpdesk.model.User;
import helpdesk.service.TicketService;
import helpdesk.service.UserService;

import java.util.*;

public class ConsoleUi {
    private final TicketService ticketService;
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);
    private User currentUser;

    public ConsoleUi(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    public void start(){
        currentUser = userService.createUser("Александр");
        boolean workingCycle = true;
        while (workingCycle){
            System.out.println("введите число: 0 - выйти, 1 - создать заявку, 2 - посмотреть все заявки,\n" +
                    " 3 - найти заявки по id, 4 - изменить статус заявки,\n 5 - создать пользователя" +
                    " 6 - выбрать пользователя,\n 7 - посмотреть пользователей, 8 - написать комментарий к заявке\n" +
                    " 9 - посмотреть все комментарии к заявке, 10 - назначить ответственного,\n 11 - снять ответственного" +
                    " 12 - показать заявки конкретного человека");
            int num = scanner.nextInt();
            scanner.nextLine();

            switch (num) {
                case 0:
                    workingCycle = false;
                    break;
                case 1:
                    createTicket();
                    break;
                case 2:
                    showAllTickets();
                    break;
                case 3:
                    findTicket();
                    break;
                case 4:
                    changeStatus();
                    break;
                case 5:
                    createUser();
                    break;
                case 6:
                    chooseUser();
                    break;
                case 7:
                    showAllUsers();
                    break;
                case 8:
                    createComment();
                    break;
                case 9:
                    showTicketComments();
                    break;
                case 10:
                    assigneeOnTicket();
                    break;
                case 11:
                    removeAssigne();
                    break;
               case 12:
                    showTicketsOfPerson();
                    break;
                default:
                    System.out.println("вы ввели не то!");
                    break;
            }
        }
    }
    private void createTicket(){
        System.out.println("введите заголовок");
        String title = scanner.nextLine();
        System.out.println("опишите вашу проблему");
        String description = scanner.nextLine();
        ticketService.createTicket(title, description, currentUser);
    }
    private void showAllTickets(){
        for (Ticket ticket1 : ticketService.getAllTickets()){
            System.out.println(ticket1);
        }
    }
    private void findTicket(){
        System.out.println("введите id нужной заявки");
        long findingId = scanner.nextLong();
        System.out.println(ticketService.findById(findingId));
    }
    private void changeStatus(){
        System.out.println("введите id нужной заявки");
        long findingId = scanner.nextLong();
        System.out.println("введите число для изменения статуса заявки (1 - open, 2 - in_progress, 3 - closed)");
        TicketStatus newStatus = readStatus();
        Ticket updatedTicket = ticketService.changeStatus(findingId, newStatus);
        if ((updatedTicket != null) && (newStatus != null)) {
            System.out.println("статус изменен");
        } else if (newStatus != null) {
            System.out.println("заявки с таким id не существует!");
        }
    }
    private TicketStatus readStatus(){
        int statusChoice = scanner.nextInt();
        TicketStatus newStatus = null;
        switch (statusChoice) {
            case 1:
                newStatus = TicketStatus.OPEN;
                break;
            case 2:
                newStatus = TicketStatus.IN_PROGRESS;
                break;
            case 3:
                newStatus = TicketStatus.CLOSED;
                break;
            default:
                System.out.println("вы ввели не то число");
                newStatus = null;
                break;
        }
        return newStatus;
    }

    private void createUser(){
        System.out.println("введите имя пользователя");
        String userName = scanner.nextLine();
        userService.createUser(userName);
    }
    private void showAllUsers(){
        for (User user1 : userService.getAllUsers()){
            System.out.println(user1);
        }
    }
    private void chooseUser(){
        showAllUsers();
        System.out.println("выберите номер пользователя");
        long chosenUserId = scanner.nextLong();
        User foundUser = userService.findUserById(chosenUserId);
        if (foundUser == null) {
            System.out.println("Пользователь не найден");
            return;
        }
        currentUser = foundUser;
        System.out.println("Пользователь выбран");
    }

    private void createComment(){
        System.out.println("введите номер заявки");
        long idComment = scanner.nextLong();
        scanner.nextLine();
        System.out.println("введите комментарий");
        String comment = scanner.nextLine();
        ticketService.addComment(idComment, comment, currentUser);
    }
    private void showTicketComments(){
        System.out.println("введите номер заявки для просмотра ее комментариев");
        long idComment = scanner.nextLong();
        List<Comment> comments = ticketService.getComments(idComment);
        if (comments.isEmpty()) {
            System.out.println("Комментарии отсутствуют");
            return;
        }
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    private void assigneeOnTicket(){
        System.out.println("введите номер заявки");
        long idticket = scanner.nextLong();
        scanner.nextLine();
        System.out.println("введите номер ответственного за заявку");
        long userId = scanner.nextLong();
        User user = userService.findUserById(userId);
        if (user == null) {
            System.out.println("Пользователь не найден");
            return;
        }
        ticketService.setAssignee(idticket, user);
    }
    private void removeAssigne(){
        System.out.println("введите номер заявки");
        long idticket = scanner.nextLong();
        scanner.nextLine();
        System.out.println(ticketService.findById(idticket));
        System.out.println("подтвердите снятие - 1 да, 2 - нет");
        long removing = scanner.nextLong();
        boolean removed = false;
        if (removing == 1) {
            removed = ticketService.removeAssignee(idticket);
        }
        if (removed) {
            System.out.println("пользователь снят");
        } else {
            System.out.println("заявка не найдена");
        }


    }
    private void showTicketsOfPerson() {
        showAllUsers();

        System.out.println("Введите id пользователя");

        long id = scanner.nextLong();

        List<Ticket> ticketsBufer =
                ticketService.findTicketsByAuthor(id);

        for (Ticket ticket : ticketsBufer) {
            System.out.println(ticket);
        }
    }




}
