package client.main.java.view.FXML.edu.demand.professor.minor;

import client.main.java.view.GUIController;
import shared.request.edu.demand.action.AnswerMinorRequest;

public class MinorListListener {
    public void listen(Integer id, boolean result) {
        GUIController.getController().getListener().listen(new AnswerMinorRequest(id, result));
    }
}
