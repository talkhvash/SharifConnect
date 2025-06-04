package shared.response.edu.demand;

import shared.model.edu.demand.WithdrawDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

public class RefreshWithdrawResponse extends Response {
    private WithdrawDemand demand;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshWithdrawView(demand);
    }

    public WithdrawDemand getDemand() {
        return demand;
    }

    public void setDemand(WithdrawDemand demand) {
        this.demand = demand;
    }
}
