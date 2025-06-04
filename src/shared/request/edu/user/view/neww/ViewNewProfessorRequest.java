package shared.request.edu.user.view.neww;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.newuser.ViewNewProfessorResponse;

public class ViewNewProfessorRequest extends Request {

    @Override
    public ViewNewProfessorResponse handle(RequestHandler handler) {
        return handler.viewNewProfessor();
    }

}
