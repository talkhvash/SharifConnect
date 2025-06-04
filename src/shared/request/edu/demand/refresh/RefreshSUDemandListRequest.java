package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshSUDemandListResponse;

public class RefreshSUDemandListRequest extends Request {
    @Override
    public RefreshSUDemandListResponse handle(RequestHandler handler) {
        return handler.refreshSUDemandList();
    }
}
