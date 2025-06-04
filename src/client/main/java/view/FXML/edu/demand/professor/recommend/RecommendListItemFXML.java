package client.main.java.view.FXML.edu.demand.professor.recommend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import client.main.java.db.ModelLoader;
import shared.model.edu.demand.RecommendDemand;
import shared.model.edu.user.User;

public class RecommendListItemFXML {
    private final RecommendListListener listener = new RecommendListListener();
    @FXML
    private Button confirmButton,
            rejectButton;
    @FXML
    private Label nameLabel,
            stateLabel;
    @FXML
    private HBox box;

    private RecommendDemand demand;
    public void initialize(RecommendDemand demand) {
        this.demand = demand;
        User user = ModelLoader.getLoader().loadUser(demand.getDemanderId());
        if (user != null) nameLabel.setText(user.getName());

        if (demand.getState() == null) {
            box.getChildren().remove(stateLabel);
        } else {
            box.getChildren().remove(rejectButton);
            box.getChildren().remove(confirmButton);
            if (demand.getState()) stateLabel.setText("تایید شده");
            else stateLabel.setText("رد شده");
        }
    }

    @FXML
    private void confirm() {
        listener.listen(demand.getId(), true);
    }
    @FXML
    private void reject() {
        listener.listen(demand.getId(), false);
    }
}
