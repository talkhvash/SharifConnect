package client.main.java.view.FXML.edu.demand.professor.withdraw;

import client.main.java.view.GUIController;
import shared.request.edu.demand.action.AnswerWithdrawRequest;

public class WithdrawListListener {
    public void listen(Integer id, boolean result) {
        GUIController.getController().getListener().listen(new AnswerWithdrawRequest(id, result));
    }
}
