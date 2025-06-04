package client.main.java.view.FXML.edu.demand.professor.withdraw;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import shared.model.edu.demand.WithdrawDemand;

import java.util.LinkedList;

public class WithdrawListFXML {

    @FXML
    private VBox box;

    public void refresh(LinkedList<WithdrawDemand> list) {
        box.getChildren().clear();
        for (WithdrawDemand item : list) {
            WithdrawListItem temp = new WithdrawListItem();
            temp.getFXML().initialize(item);
            box.getChildren().add(temp.getHBox());
        }
    }

}
