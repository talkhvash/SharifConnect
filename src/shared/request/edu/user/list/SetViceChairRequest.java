package shared.request.edu.user.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class SetViceChairRequest extends Request {
    private final Integer id;

    public SetViceChairRequest(int id) {
        this.id = id;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.setViceChair(id);
        return new EmptyResponse();
    }
}
