package shared.request.edu.user.view.edit;

import shared.model.edu.user.Professor;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.edit.EditProfessorResponse;

public class EditProfessorRequest extends Request {
    private final int professorId;
    private final Professor professor;

    public EditProfessorRequest(int professorId, Professor professor) {
        this.professorId = professorId;
        this.professor = professor;
    }

    @Override
    public EditProfessorResponse handle(RequestHandler handler) {
        return handler.editProfessor(professor, professorId);
    }

    // getters and setters

    public int getProfessorId() {
        return professorId;
    }

    public Professor getProfessor() {
        return professor;
    }

}
