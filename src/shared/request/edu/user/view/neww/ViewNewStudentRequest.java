package shared.request.edu.user.view.neww;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.newuser.ViewNewStudentResponse;

public class ViewNewStudentRequest extends Request {

    @Override
    public ViewNewStudentResponse handle(RequestHandler handler) {
        return handler.viewNewStudent();
    }

}
