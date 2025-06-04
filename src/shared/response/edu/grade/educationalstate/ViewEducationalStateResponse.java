package shared.response.edu.grade.educationalstate;

import shared.model.edu.user.User;
import shared.response.Response;
import shared.response.ResponseHandler;

public class ViewEducationalStateResponse extends Response {
    private User viewer;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewEducationalState(this);
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }
}
