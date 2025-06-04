package client.main.java.view.FXML.myedu.professor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import shared.model.edu.user.Student;

import java.time.format.DateTimeFormatter;

public class ProfessorSelectUnitListItemFXML {

    @FXML
    private Label nameLabel,
            yearLabel,
            degreeLabel,
            timeLabel,
            randLabel;

    public void refresh(Student student) {
        try {
            nameLabel.setText(student.getName());
            yearLabel.setText(student.getYear().toString());
            degreeLabel.setText(student.getDegree().toString());
            if (student.getSelectUnitTime() == null) {
                timeLabel.setText("تعیین نشده");
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");
                timeLabel.setText(formatter.format(student.getSelectUnitTime()));
            }
            randLabel.setText(student.getRand().toString());
        } catch (NullPointerException ignored) {
        }
    }


}
