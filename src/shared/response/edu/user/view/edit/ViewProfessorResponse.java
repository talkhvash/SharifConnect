package shared.response.edu.user.view.edit;

import shared.model.edu.user.Professor;
import shared.response.Response;
import shared.response.ResponseHandler;

public class ViewProfessorResponse extends Response {
    private Professor professor;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewProfessor(this);
    }

    // getters and setters
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
