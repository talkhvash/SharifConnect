package client.main.java.view.FXML.edu.demand.professor.minor;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import shared.model.edu.demand.MinorDemand;

import java.util.LinkedList;

public class MinorListFXML {

    @FXML
    private VBox box;

    public void refresh(LinkedList<MinorDemand> listIn, LinkedList<MinorDemand> listOut) {
        box.getChildren().clear();
        for (MinorDemand item : listIn) {
            MinorListItem temp = new MinorListItem();
            temp.getFXML().initializeIn(item);
            box.getChildren().add(temp.getHBox());
        }
        for (MinorDemand item : listOut) {
            MinorListItem temp = new MinorListItem();
            temp.getFXML().initializeOut(item);
            box.getChildren().add(temp.getHBox());
        }
    }

}
