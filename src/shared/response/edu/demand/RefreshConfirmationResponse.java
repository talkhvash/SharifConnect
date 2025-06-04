package shared.response.edu.demand;

import shared.model.edu.demand.ConfirmationDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

public class RefreshConfirmationResponse extends Response {
    private ConfirmationDemand demand;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshConfirmationView(demand);
    }

    public ConfirmationDemand getDemand() {
        return demand;
    }

    public void setDemand(ConfirmationDemand demand) {
        this.demand = demand;
    }
}
