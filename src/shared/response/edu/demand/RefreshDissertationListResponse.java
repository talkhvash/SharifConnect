package shared.response.edu.demand;

import shared.model.edu.demand.DissertationDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshDissertationListResponse extends Response {
    private LinkedList<DissertationDemand> list;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshDissertationList(list);
    }

    public LinkedList<DissertationDemand> getList() {
        return list;
    }

    public void setList(LinkedList<DissertationDemand> list) {
        this.list = list;
    }
}
