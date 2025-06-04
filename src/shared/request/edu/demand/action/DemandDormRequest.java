package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class DemandDormRequest extends Request {
    @Override
    public Response handle(RequestHandler handler) {
        handler.demandDorm();
        return new EmptyResponse();
    }
}
