package shared.response.edu.main;

import shared.model.edu.user.User;
import shared.response.Response;
import shared.response.ResponseHandler;

public class RefreshMainPaneResponse extends Response {
    private User user;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshMainPane(this);
    }

    // getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
