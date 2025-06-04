package shared.request.edu.demand.refresh;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.demand.RefreshRecommendResponse;

public class RefreshRecommendRequest extends Request {

    @Override
    public RefreshRecommendResponse handle(RequestHandler handler) {
        return handler.refreshRecommendView();
    }
}
