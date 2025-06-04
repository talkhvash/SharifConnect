package client.main.java.view.FXML.edu.demand.professor.minor;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import client.main.java.db.ModelLoader;
import shared.model.edu.Department;
import shared.model.edu.demand.MinorDemand;
import shared.model.edu.user.User;

public class MinorListItemFXML {
    private final MinorListListener listener = new MinorListListener();
    @FXML
    private Label inLabel,
            outLabel,
            nameLabel,
            stateLabel,
            departmentStateLabel;
    @FXML
    private HBox box;
    @FXML
    private VBox stateBox;
    @FXML
    private CheckBox confirm,
            reject;

    private MinorDemand demand;

    public void initializeIn(MinorDemand demand) {
        this.demand = demand;
        initialize(demand);

        if (demand.getOutState() == null) departmentStateLabel.setText("ثبت شده");
        else if (demand.getOutState()) departmentStateLabel.setText("تایید شده");
        else departmentStateLabel.setText("رد شده");

        box.getChildren().add(2, departmentStateLabel);
        box.getChildren().add(5, stateBox);

        confirm.setSelected(false);
        reject.setSelected(false);
        if (demand.getInState() != null) {
            if (demand.getOutState()) {
                confirm.setSelected(true);
            } else {
                reject.setSelected(true);
            }
        }

    }

    public void initializeOut(MinorDemand demand) {
        initialize(demand);

        if (demand.getInState() == null) departmentStateLabel.setText("ثبت شده");
        else if (demand.getInState()) departmentStateLabel.setText("تایید شده");
        else departmentStateLabel.setText("رد شده");

        box.getChildren().add(2, stateBox);
        box.getChildren().add(5, departmentStateLabel);

        confirm.setSelected(false);
        reject.setSelected(false);
        if (demand.getOutState() != null) {
            if (demand.getOutState()) {
                confirm.setSelected(true);
            } else {
                reject.setSelected(true);
            }
        }

    }

    private void initialize(MinorDemand demand) {
        User user = ModelLoader.getLoader().loadUser(demand.getDemanderId());
        if (user != null) nameLabel.setText(user.getName());
        Department out = ModelLoader.getLoader().loadDepartment(demand.getOutDepartmentId());
        if (out != null) outLabel.setText(out.getName());
        Department in = ModelLoader.getLoader().loadDepartment(demand.getInDepartmentId());
        if (in != null) inLabel.setText(in.getName());

        if (demand.getState() == null) stateLabel.setText("ثبت شده");
        else if (demand.getOutState()) stateLabel.setText("تایید شده");
        else stateLabel.setText("رد شده");

        box.getChildren().remove(stateBox);
        box.getChildren().remove(departmentStateLabel);
    }

    @FXML
    private void reject() {
        confirm.setSelected(!reject.isSelected());
        listener.listen(demand.getId(), confirm.isSelected());
    }

    @FXML
    private void confirm() {
        reject.setSelected(!confirm.isSelected());
        listener.listen(demand.getId(), confirm.isSelected());
    }
}
