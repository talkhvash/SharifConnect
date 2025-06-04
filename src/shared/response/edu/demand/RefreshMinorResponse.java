package shared.response.edu.demand;

import shared.model.edu.demand.MinorDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

public class RefreshMinorResponse extends Response {
    private MinorDemand demand;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshMinorView(demand);
    }

    public MinorDemand getDemand() {
        return demand;
    }

    public void setDemand(MinorDemand demand) {
        this.demand = demand;
    }
}
