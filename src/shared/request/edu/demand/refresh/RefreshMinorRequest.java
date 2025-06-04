package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshMinorResponse;

public class RefreshMinorRequest extends Request {

    @Override
    public RefreshMinorResponse handle(RequestHandler handler) {
        return handler.refreshMinorView();
    }
}
