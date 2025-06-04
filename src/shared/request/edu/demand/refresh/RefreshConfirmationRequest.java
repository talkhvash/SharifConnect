package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshConfirmationResponse;

public class RefreshConfirmationRequest extends Request {
    @Override
    public RefreshConfirmationResponse handle(RequestHandler handler) {
        return handler.refreshConfirmationView();
    }
}
