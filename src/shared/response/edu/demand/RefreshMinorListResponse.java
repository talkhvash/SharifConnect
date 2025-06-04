package shared.response.edu.demand;

import shared.model.edu.demand.MinorDemand;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshMinorListResponse extends Response {
    private LinkedList<MinorDemand> listIn;
    private LinkedList<MinorDemand> listOut;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshMinorList(listIn, listOut);
    }

    public LinkedList<MinorDemand> getListIn() {
        return listIn;
    }

    public void setListIn(LinkedList<MinorDemand> listIn) {
        this.listIn = listIn;
    }

    public LinkedList<MinorDemand> getListOut() {
        return listOut;
    }

    public void setListOut(LinkedList<MinorDemand> listOut) {
        this.listOut = listOut;
    }
}
