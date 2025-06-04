package client.main.java.view.FXML.edu.user.list.student;

import client.main.java.view.FXML.myedu.Listener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.model.edu.user.Student;

public class StudentListItemFXML {
    private Listener listener;

    @FXML
    private Label nameLabel,
            degreeLabel,
            idLabel;

    private int index;
    public void initialize(Student student, int index) {
        nameLabel.setText(student.getName());
        degreeLabel.setText(student.getDegree().toString());
        idLabel.setText(student.getId() + "");
        this.index = index;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @FXML
    private void showProfile() {
        listener.listen(index);
    }
}
