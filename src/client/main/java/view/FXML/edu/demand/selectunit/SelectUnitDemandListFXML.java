package client.main.java.view.FXML.edu.demand.selectunit;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import shared.model.edu.demand.SelectUnitDemand;

import java.util.LinkedList;

public class SelectUnitDemandListFXML {

    @FXML
    private VBox box;

    public void refresh(LinkedList<SelectUnitDemand> demandsList, boolean answerView) {
        box.getChildren().clear();
        for (SelectUnitDemand demand : demandsList) {
            SelectUnitDemandListItem item = new SelectUnitDemandListItem();
            item.getFXML().initialize(demand, answerView);
            box.getChildren().add(item.getHBox());
        }
    }
}
