package shared.model.messenger;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Chat {
    private int id;
    private int user1Id;
    private int user2Id;
    private LinkedList<Integer> messagesId = new LinkedList<>();
    private LocalDateTime laseMessageDt;
    private String lastMessageText;
    private boolean user1seen;
    private boolean user2seen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    public LinkedList<Integer> getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(LinkedList<Integer> messagesId) {
        this.messagesId = messagesId;
    }

    public LocalDateTime getLaseMessageDt() {
        return laseMessageDt;
    }

    public void setLaseMessageDt(LocalDateTime laseMessageDt) {
        this.laseMessageDt = laseMessageDt;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public void setLastMessageText(String lastMessageText) {
        this.lastMessageText = lastMessageText;
    }

    public boolean isUser1seen() {
        return user1seen;
    }

    public void setUser1seen(boolean user1seen) {
        this.user1seen = user1seen;
    }

    public boolean isUser2seen() {
        return user2seen;
    }

    public void setUser2seen(boolean user2seen) {
        this.user2seen = user2seen;
    }
}
