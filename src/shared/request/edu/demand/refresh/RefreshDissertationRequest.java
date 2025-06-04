package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshDissertationResponse;

public class RefreshDissertationRequest extends Request {
    @Override
    public RefreshDissertationResponse handle(RequestHandler handler) {
        return handler.refreshDissertationView();
    }
}
