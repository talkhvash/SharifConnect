package client.main.java.view.FXML.edu.demand.selectunit;

import client.main.java.view.GUIController;
import shared.request.edu.demand.action.AnswerSelectUnitDemandRequest;

public class SelectUnitDemandListListener {

    public void listen(int id, boolean result) {
        GUIController.getController().getListener().listen(new AnswerSelectUnitDemandRequest(result, id));
    }
}
