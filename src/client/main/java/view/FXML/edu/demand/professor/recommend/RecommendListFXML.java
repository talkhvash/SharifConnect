package client.main.java.view.FXML.edu.demand.professor.recommend;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import shared.model.edu.demand.RecommendDemand;

import java.util.LinkedList;

public class RecommendListFXML {
    @FXML
    private VBox box;

    public void refresh(LinkedList<RecommendDemand> list) {
        box.getChildren().clear();
        for (RecommendDemand item : list) {
            RecommendListItem temp = new RecommendListItem();
            temp.getFXML().initialize(item);
            box.getChildren().add(temp.getHBox());
        }
    }
}
