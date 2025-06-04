package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshRecommendListResponse;

public class RefreshRecommendListRequest extends Request {

    @Override
    public RefreshRecommendListResponse handle(RequestHandler handler) {
        return handler.refreshRecommendList();
    }
}
