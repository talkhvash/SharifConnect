package client.main.java.view.FXML.edu.demand.student.dissertation;

import client.main.java.view.FXML.edu.demand.student.DemandListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import shared.model.edu.demand.DissertationDemand;

import java.time.format.DateTimeFormatter;

public class DissertationViewFXML {
    private final DemandListener listener = new DemandListener();

    @FXML
    private Label label1,
            label2;
    @FXML
    private HBox stateBox;
    @FXML
    private Button demandButton;

    public void refresh(DissertationDemand demand) {

        if (demand == null) {
            stateBox.setVisible(false);
            demandButton.setVisible(true);
        } else {
            demandButton.setVisible(false);
            stateBox.setVisible(true);
            if (demand.getDateTime() == null) {
                label1.setText("وضعیت درخواست:");
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                label2.setText(formatter.format(demand.getDateTime()));
            }
        }

    }

    @FXML
    private void demand() {
        listener.listen("dissertation", null);
    }
}
