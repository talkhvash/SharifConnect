package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshDormResponse;

public class RefreshDormRequest extends Request {

    @Override
    public RefreshDormResponse handle(RequestHandler handler) {
        return handler.refreshDormView();
    }
}
