package shared.request.edu.user.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class DeleteProfessorRequest extends Request {
    private final int professorId;

    public DeleteProfessorRequest(int professorId) {
        this.professorId = professorId;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.deleteProfessor(professorId);
        return new EmptyResponse();
    }

}
