package shared.request.edu.user.view.neww;

import shared.model.edu.user.Professor;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.newuser.NewProfessorResponse;

public class NewProfessorRequest extends Request {
    private final Professor professor;

    public NewProfessorRequest(Professor professor) {
        this.professor = professor;
    }

    @Override
    public NewProfessorResponse handle(RequestHandler handler) {
        return handler.newProfessor(professor);
    }

    public Professor getProfessor() {
        return professor;
    }

}
