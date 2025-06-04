package shared.response.edu.demand;

import shared.model.edu.demand.DissertationDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

public class RefreshDissertationResponse extends Response {
    private DissertationDemand demand;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshDissertationView(demand);
    }

    public DissertationDemand getDemand() {
        return demand;
    }

    public void setDemand(DissertationDemand demand) {
        this.demand = demand;
    }
}
