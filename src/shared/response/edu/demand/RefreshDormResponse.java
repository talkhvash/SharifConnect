package shared.response.edu.demand;

import shared.model.edu.demand.DormDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

public class RefreshDormResponse extends Response {
    private DormDemand demand;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshDormView(demand);
    }

    public DormDemand getDemand() {
        return demand;
    }

    public void setDemand(DormDemand demand) {
        this.demand = demand;
    }
}
