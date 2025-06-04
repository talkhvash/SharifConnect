package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshWithdrawListResponse;

public class RefreshWithdrawListRequest extends Request {

    @Override
    public RefreshWithdrawListResponse handle(RequestHandler handler) {
        return handler.refreshWithdrawList();
    }
}
