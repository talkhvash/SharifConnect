package shared.response.edu.main;

import shared.model.edu.user.User;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.time.LocalDateTime;

public class RefreshMainMenuResponse extends Response {
    private LocalDateTime time = LocalDateTime.now();
    private User viewer;
    private boolean selectUnitMenuItem;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshMainMenu(this);
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }

    public boolean isSelectUnitMenuItem() {
        return selectUnitMenuItem;
    }

    public void setSelectUnitMenuItem(boolean selectUnitMenuItem) {
        this.selectUnitMenuItem = selectUnitMenuItem;
    }
}
