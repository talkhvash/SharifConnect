package shared.response.edu.user.list;

import shared.model.edu.user.Professor;
import shared.model.edu.user.User;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshProfessorListResponse extends Response {
    private LinkedList<Professor> list;
    private User viewer;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshProfessorList(this);
    }

    public LinkedList<Professor> getList() {
        return list;
    }

    public void setList(LinkedList<Professor> list) {
        this.list = list;
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }
}
