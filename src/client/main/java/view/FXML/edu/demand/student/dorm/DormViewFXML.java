package client.main.java.view.FXML.edu.demand.student.dorm;

import client.main.java.view.FXML.edu.demand.student.DemandListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import shared.model.edu.demand.DormDemand;

public class DormViewFXML {
    private final DemandListener listener = new DemandListener();
    @FXML
    private HBox stateBox;
    @FXML
    private Label stateLabel;
    @FXML
    private Button demandButton;

    public void refresh(DormDemand demand) {
        if (demand == null) {
            stateBox.setVisible(false);
            demandButton.setVisible(true);
        } else {
            stateBox.setVisible(true);
            demandButton.setVisible(false);

            if (demand.getState() == null) stateLabel.setText("ثبت شده");
            else if (demand.getState()) stateLabel.setText("تایید شده");
            else stateLabel.setText("رد شده");
        }
    }

    @FXML
    private void demand() {
        listener.listen("dorm", null);
    }

}
