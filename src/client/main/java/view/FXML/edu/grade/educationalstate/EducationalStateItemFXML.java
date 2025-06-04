package client.main.java.view.FXML.edu.grade.educationalstate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import client.main.java.db.ModelLoader;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;

public class EducationalStateItemFXML {

    @FXML
    private Label nameLabel,
            unitLabel,
            gradeLabel;

    public void initialize(Grade grade) {
        Course course = ModelLoader.getLoader().loadCourse(grade.getCourseId());
        if (course != null) {
            nameLabel.setText(course.getName());
        }
        if (grade.getFinalizedNumber() != null && grade.getFinalizedNumber()) {
            unitLabel.setText(grade.getUnit().getNumber() + "");
            gradeLabel.setText(grade.getNumber() + "");
        } else {
            gradeLabel.setText("N/A");
        }
    }

}
