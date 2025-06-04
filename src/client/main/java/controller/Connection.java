package client.main.java.controller;

import shared.model.edu.user.User;

public class Connection {
    private static Connection status;

    public static Connection getStatus() {
        if (status == null) status = new Connection();
        return status;
    }

    private boolean online;
    private User user;

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
