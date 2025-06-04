package client.main.java.view.FXML.myedu.changegroup;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import client.main.java.view.FXML.myedu.Listener;
import shared.model.edu.course.Course;

public class ChangeGroupListItemFXML {


    private Listener listener;
    @FXML
    private Label groupLabel,
        capacityLabel;

    private int id;

    public void initialize(Course course) {
        id = course.getId();
        groupLabel.setText(course.getGroup() + "");
        capacityLabel.setText(course.getCapacityCount() + " (" + course.getStudentCount() + " )");
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
