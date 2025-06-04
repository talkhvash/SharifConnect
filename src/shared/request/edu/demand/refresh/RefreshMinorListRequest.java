package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshMinorListResponse;

public class RefreshMinorListRequest extends Request {

    @Override
    public RefreshMinorListResponse handle(RequestHandler handler) {
        return handler.refreshMinorList();
    }
}
