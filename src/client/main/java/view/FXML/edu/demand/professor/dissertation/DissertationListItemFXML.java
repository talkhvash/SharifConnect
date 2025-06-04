package client.main.java.view.FXML.edu.demand.professor.dissertation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import client.main.java.db.ModelLoader;
import shared.model.edu.demand.DissertationDemand;
import shared.model.edu.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DissertationListItemFXML {
    private final DissertationListListener listener = new DissertationListListener();
    @FXML
    private Label nameLabel,
            dateLabel;
    @FXML
    private HBox setBox,
            box;
    @FXML
    private TextField hour,
            minute;
    @FXML
    private Button setButton;
    @FXML
    private DatePicker date;


    private DissertationDemand demand;
    public void initialize(DissertationDemand demand) {
        this.demand = demand;
        User user = ModelLoader.getLoader().loadUser(demand.getDemanderId());
        if (user != null) nameLabel.setText(user.getName());

        LocalDateTime dt = demand.getDateTime();
        if (demand.isFinalized()) {
            box.getChildren().remove(setBox);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            dateLabel.setText(formatter.format(demand.getDateTime()));
        } else if (dt != null) {
            box.getChildren().remove(dateLabel);
            hour.setText(dt.getHour() + "");
            minute.setText(dt.getMinute() + "");
            date.setValue(LocalDate.from(dt));
        }
    }

    @FXML
    private void set() {
        try {
            int hou = Integer.parseInt(hour.getText());
            int min = Integer.parseInt(minute.getText());
            LocalDate dateValue = date.getValue();
            LocalDateTime output = LocalDateTime.of(dateValue, LocalTime.of(hou,min));
            listener.listen(demand.getId(), output);
        } catch (Exception ignored) {

        }
    }

}
