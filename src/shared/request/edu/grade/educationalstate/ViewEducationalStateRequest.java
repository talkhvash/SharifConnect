package shared.request.edu.grade.educationalstate;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.grade.educationalstate.ViewEducationalStateResponse;

public class ViewEducationalStateRequest extends Request {
    @Override
    public ViewEducationalStateResponse handle(RequestHandler handler) {
        return handler.viewEducationalState();
    }
}
