package client.main.java.view.FXML.edu.demand.professor.recommend;

import client.main.java.view.GUIController;
import shared.request.edu.demand.action.AnswerRecommendRequest;

public class RecommendListListener {
    public void listen(Integer id, boolean result) {
        GUIController.getController().getListener().listen(new AnswerRecommendRequest(id, result));
    }
}
