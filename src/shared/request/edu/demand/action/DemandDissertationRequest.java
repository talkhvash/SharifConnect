package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class DemandDissertationRequest extends Request {
    @Override
    public Response handle(RequestHandler handler) {
        handler.demandDissertation();
        return new EmptyResponse();
    }
}
