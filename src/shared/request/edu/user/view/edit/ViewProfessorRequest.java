package shared.request.edu.user.view.edit;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.edit.ViewProfessorResponse;

public class ViewProfessorRequest extends Request {
    private final int professorId;

    public ViewProfessorRequest(int professorId) {
        this.professorId = professorId;
    }

    @Override
    public ViewProfessorResponse handle(RequestHandler handler) {
        return handler.viewProfessor(professorId);
    }

    // getters and setters

    public int getProfessorId() {
        return professorId;
    }

}
