package server.main.java.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import server.main.java.db.DB;
import shared.config.Config;
import shared.model.edu.Department;
import shared.model.edu.user.MrMohseni;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.model.messenger.Chat;
import shared.model.messenger.Message;
import shared.request.FilterStudentForm;
import shared.request.messenger.SendMessageRequest;
import shared.response.messenger.RefreshChatroomResponse;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class MessengerController {
    private final static Logger log = LogManager.getLogger(MessengerController.class);

    private final DB db = DB.getDB();

    public RefreshChatroomResponse refreshChatroom(int userId) {
        RefreshChatroomResponse response = new RefreshChatroomResponse();
        try {
            User user = db.getUser(userId);
            response.setMessages(getMessages(user));
            response.setChats(getChats(user));
            response.setUsers(getUsers(user));
        } catch (Exception e) {
            log.error("while refresh chatroom user: " + userId);
        }
        return response;
    }

    private HashMap<Integer, LinkedList<Message>> getMessages(User user) throws Exception {
        HashMap<Integer, LinkedList<Message>> output = new HashMap<>();
        for (Integer chatId : user.getChatsId()) {
            Chat chat = db.getChat(chatId);
            LinkedList<Message> add = new LinkedList<>();
            for (Integer messageId : chat.getMessagesId()) {
                Message message = db.getMessage(messageId);
                add.add(message);
            }
            output.put(chatId, add);
        }
        return output;
    }

    private LinkedList<Chat> getChats(User user) throws Exception {
        LinkedList<Chat> output = new LinkedList<>();
        for (Integer chatId : user.getChatsId()) {
            Chat add = db.getChat(chatId);
            output.add(add);
        }
        return output;
    }

    private LinkedList<User> getUsers(User user) throws Exception {
        LinkedList<User> output = new LinkedList<>();
        if (user instanceof Student) {
            Student student = (Student) user;
            // supervisor
            output.add(db.getUser(student.getSupervisorId()));
            // students
            FilterStudentForm form = new FilterStudentForm();
            form.setYear(student.getYear());
            form.setDepartmentId(student.getDepartmentId());
            output.addAll(db.filterStudent(form));

        } else if (user instanceof Professor) {
            Professor professor = (Professor) user;
            if (professor.isViceChair() || professor.isChairman()) {
                FilterStudentForm form = new FilterStudentForm();
                form.setDepartmentId(professor.getDepartmentId());
                output.addAll(db.filterStudent(form));
            } else {
                output.addAll(db.getUser(professor.getSuperVisorsId()));
                // vice chair
                Department department = db.getDepartment(professor.getDepartmentId());
                output.add(db.getUser(department.getViceChairId()));
                output.add(db.getUser(department.getChairManId()));
            }
        } else if (user instanceof MrMohseni) {
            for (User item : db.allUsers()) {
                if (item instanceof Student) output.add(item);
            }
            output.sort(Comparator.comparingInt(o -> ((Student) o).getYear().getNumber()));
        }
        return normalize(output);
    }

    private LinkedList<User> normalize(LinkedList<User> input) {
        LinkedList<User> output = new LinkedList<>();
        for (User user : input) {
            if (user instanceof Student) {
                Student student = new Student();
                student.setFirstName(user.getFirstName());
                student.setLastName(user.getLastName());
                student.setId(user.getId());
                output.add(student);
            } else if (user instanceof Professor) {
                Professor professor = new Professor();
                professor.setFirstName(user.getFirstName());
                professor.setLastName(user.getLastName());
                professor.setId(user.getId());
                professor.setViceChair(((Professor) user).isViceChair());
                professor.setChairman(((Professor) user).isChairman());
                output.add(professor);
            }
        }
        return output;
    }

    public void sendMessage(SendMessageRequest request, Integer userId) {
        Message message = new Message();
        message.setId(getMessageId());
        message.setDt(LocalDateTime.now());
        message.setText(request.getText());
        message.setFileBase64(request.getFileBase64());
        message.setSenderId(userId);
        try {
            if (request.getChatId() != null) {
                message.setChatId(request.getChatId());
                sendMessageToChat(message);
            } else if (request.getContactId() != null) {
                message.setContactId(request.getContactId());
                sendMessageToUser(message);
            } else if (request.getIds() != null) {
                for (Integer id : request.getIds()) {
                    message.setContactId(id);
                    sendMessageToUser(message);
                }
            } else {
                FilterStudentForm form = new FilterStudentForm();
                form.setRand(request.getRand());
                form.setDegree(request.getDegree());
                form.setYear(request.getYear());
                LinkedList<Student> students = db.filterStudent(form);
                for (Student student : students) {
                    message.setContactId(student.getId());
                    sendMessageToUser(message);
                }
            }
        } catch (Exception e) {
            log.error("while sending message user: " + userId);
        }

    }

    private void sendMessageToChat(Message message) throws Exception {
        Chat chat = db.getChat(message.getChatId());
        chat.getMessagesId().add(message.getId());
        chat.setLastMessageText(message.getText());
        chat.setLaseMessageDt(message.getDt());

        message.setContactId(message.getSenderId() == chat.getUser1Id() ? chat.getUser2Id() : chat.getUser1Id());

        db.addMessage(message);
        db.updateChat(chat);
    }

    private void sendMessageToUser(Message message) throws Exception {
        User contact = db.getUser(message.getContactId());
        if (contact == null) return;

        for (Chat chat : db.getChat(contact.getChatsId())) {
            if (chat.getUser1Id() == message.getSenderId() || chat.getUser2Id() == message.getSenderId()) {
                message.setChatId(chat.getId());
                sendMessageToChat(message);
                return;
            }
        }

        Chat chat = new Chat();
        chat.setUser1Id(message.getSenderId());
        chat.setUser2Id(message.getContactId());
        chat.setId(getChatId());

        User user1 = db.getUser(message.getSenderId());
        user1.getChatsId().add(chat.getId());
        User user2 = db.getUser(message.getContactId());
        user2.getChatsId().add(chat.getId());
        db.updateUser(user1);
        db.updateUser(user2);

        db.addChat(chat);

        message.setChatId(chat.getId());
        sendMessageToChat(message);
    }

    public static int getMessageId() {
        try {
            File file = new File(DB.MESSAGES_SOURCE + "/id.text");
            Scanner scanner = new Scanner(file);
            int id = Integer.parseInt(scanner.nextLine());
            scanner.close();
            FileWriter writer = new FileWriter(file);
            id++;
            writer.write(id + "");
            writer.close();
            return id - 1;
        }catch (Exception e) {
            return -1;
        }
    }

    public static int getChatId() {
        try {
            File file = new File(DB.CHATS_SOURCE + "/id.text");
            Scanner scanner = new Scanner(file);
            int id = Integer.parseInt(scanner.nextLine());
            scanner.close();
            FileWriter writer = new FileWriter(file);
            id++;
            writer.write(id + "");
            writer.close();
            return id - 1;
        }catch (Exception e) {
            return -1;
        }
    }

}
