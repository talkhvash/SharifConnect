package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshDissertationListResponse;

public class RefreshDissertationListRequest extends Request {
    @Override
    public RefreshDissertationListResponse handle(RequestHandler handler) {
        return handler.refreshDissertationList();
    }
}
