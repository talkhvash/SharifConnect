package client.main.java.view.FXML.edu.demand.student.minor;

import client.main.java.view.FXML.edu.demand.student.DemandListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import client.main.java.db.ModelLoader;
import shared.model.edu.Department;
import shared.model.edu.demand.MinorDemand;

public class MinorViewFXML {
    private final DemandListener listener = new DemandListener();
    @FXML
    private Label inDepartmentStateLabel,
            outDepartmentStateLabel,
            stateLabel,
            outDepartmentNameLabel;
    @FXML
    private TextField departmentField;
    @FXML
    private Button demandButton;
    @FXML
    private HBox demandBox,
            stateBox;

    public void refresh(MinorDemand demand) {
        if (demand == null) {
            stateBox.setVisible(false);
            demandBox.setVisible(true);
        } else {
            demandBox.setVisible(false);
            stateBox.setVisible(true);

            Department out = ModelLoader.getLoader().loadDepartment(demand.getOutDepartmentId());
            if (out != null) outDepartmentNameLabel.setText(out.getName());

            if (demand.getInState() == null) inDepartmentStateLabel.setText("ثبت شده");
            else if (demand.getInState()) inDepartmentStateLabel.setText("تایید شده");
            else inDepartmentStateLabel.setText("رد شده");

            if (demand.getOutState() == null) outDepartmentStateLabel.setText("ثبت شده");
            else if (demand.getOutState()) outDepartmentStateLabel.setText("تایید شده");
            else outDepartmentStateLabel.setText("رد شده");

            if (demand.getState() == null) stateLabel.setText("ثبت شده");
            else if (demand.getOutState()) stateLabel.setText("تایید شده");
            else stateLabel.setText("رد شده");
        }
    }

    @FXML
    private void demandMinor() {
        listener.listen("minor", departmentField.getText());
    }

}
