package shared.response.edu.demand;

import shared.model.edu.demand.SelectUnitDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshSUDemandListResponse extends Response {
    private final LinkedList<SelectUnitDemand> demandsList;
    private final boolean answerView;

    public RefreshSUDemandListResponse(LinkedList<SelectUnitDemand> demandsList, boolean answerView) {
        this.demandsList = demandsList;
        this.answerView = answerView;
    }

    @Override
    public void handle(ResponseHandler handler) {

    }
}
