package client.main.java.view.FXML.edu.demand.professor.dissertation;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import client.main.java.view.FXML.edu.demand.student.dissertation.DissertationView;
import shared.model.edu.demand.DissertationDemand;

import java.util.LinkedList;

public class DissertationListFXML {

    @FXML
    private VBox box;

    public void refresh(LinkedList<DissertationDemand> list) {
        box.getChildren().clear();
        for (DissertationDemand item : list) {
            DissertationListItem temp = new DissertationListItem();
            temp.getFXML().initialize(item);
            box.getChildren().add(temp.getHBox());
        }
    }

}
