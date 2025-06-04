package shared.response.edu.demand;

import shared.model.edu.demand.WithdrawDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshWithdrawListResponse extends Response {
    private LinkedList<WithdrawDemand> list;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshWithdrawList(list);
    }

    public LinkedList<WithdrawDemand> getList() {
        return list;
    }

    public void setList(LinkedList<WithdrawDemand> list) {
        this.list = list;
    }
}
