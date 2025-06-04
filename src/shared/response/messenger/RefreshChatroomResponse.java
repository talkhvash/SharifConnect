package shared.response.messenger;

import shared.model.edu.user.User;
import shared.model.messenger.Chat;
import shared.model.messenger.Message;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.HashMap;
import java.util.LinkedList;

public class RefreshChatroomResponse extends Response {
    private LinkedList<User> users;
    private LinkedList<Chat> chats;
    private HashMap<Integer, LinkedList<Message>> messages;
    private User viewer;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshChatroom(this);
    }

    public HashMap<Integer, LinkedList<Message>> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<Integer, LinkedList<Message>> messages) {
        this.messages = messages;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public LinkedList<Chat> getChats() {
        return chats;
    }

    public void setChats(LinkedList<Chat> chats) {
        this.chats = chats;
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }
}
