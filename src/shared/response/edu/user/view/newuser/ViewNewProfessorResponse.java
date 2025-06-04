package shared.response.edu.user.view.newuser;

import shared.model.edu.user.Professor;
import shared.response.Response;
import shared.response.ResponseHandler;

public class ViewNewProfessorResponse extends Response {
    private Professor viewer;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewNewProfessor(this);
    }

    // getters and setters

    public Professor getViewer() {
        return viewer;
    }

    public void setViewer(Professor viewer) {
        this.viewer = viewer;
    }


}
