package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshWithdrawResponse;

public class RefreshWithdrawRequest extends Request {
    @Override
    public RefreshWithdrawResponse handle(RequestHandler handler) {
        return handler.refreshWithdrawView();
    }
}
