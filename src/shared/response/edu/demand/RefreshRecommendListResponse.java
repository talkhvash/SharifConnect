package shared.response.edu.demand;

import shared.model.edu.demand.RecommendDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshRecommendListResponse extends Response {
    private LinkedList<RecommendDemand> list;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshRecommendList(list);
    }

    public LinkedList<RecommendDemand> getList() {
        return list;
    }

    public void setList(LinkedList<RecommendDemand> list) {
        this.list = list;
    }
}
