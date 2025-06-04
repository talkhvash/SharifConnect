package client.main.java.view.FXML.edu.demand.professor.dissertation;

import client.main.java.view.GUIController;
import shared.request.edu.demand.action.AnswerDissertationRequest;

import java.time.LocalDateTime;

public class DissertationListListener {
    public void listen(Integer id, LocalDateTime dt) {
        GUIController.getController().getListener().listen(new AnswerDissertationRequest(id, dt));
    }
}
