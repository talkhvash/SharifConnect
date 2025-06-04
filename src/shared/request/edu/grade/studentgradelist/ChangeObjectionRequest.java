package shared.request.edu.grade.studentgradelist;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class ChangeObjectionRequest extends Request {
    private final Integer id;
    private final String text;
    private final boolean assign;

    public ChangeObjectionRequest(Integer id, String text, boolean assign) {
        this.id = id;
        this.text = text;
        this.assign = assign;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.changeObjection(text, id, assign);
        return new EmptyResponse();
    }
}
