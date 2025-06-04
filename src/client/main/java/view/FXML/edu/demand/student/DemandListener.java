package client.main.java.view.FXML.edu.demand.student;

import client.main.java.view.GUIController;
import shared.request.edu.demand.action.*;

public class DemandListener {

    public void listen(String type, String name) {
        switch (type) {
            case "confirmation":
                GUIController.getController().getListener().listen(new DemandConfirmationRequest());
                break;
            case "dissertation":
                GUIController.getController().getListener().listen(new DemandDissertationRequest());
                break;
            case "dorm":
                GUIController.getController().getListener().listen(new DemandDormRequest());
                break;
            case "minor":
                GUIController.getController().getListener().listen(new DemandMinorRequest(name));
                break;
            case "recommendation":
                GUIController.getController().getListener().listen(new DemandRecommendRequest(name));
                break;
            case "withdraw":
                GUIController.getController().getListener().listen(new DemandWithdrawRequest());
                break;
        }
    }
}
