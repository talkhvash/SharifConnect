package shared.response.edu.demand;

import shared.model.edu.demand.RecommendDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

public class RefreshRecommendResponse extends Response {
    private RecommendDemand demand;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshRecommendView(demand);
    }

    public RecommendDemand getDemand() {
        return demand;
    }

    public void setDemand(RecommendDemand demand) {
        this.demand = demand;
    }
}
