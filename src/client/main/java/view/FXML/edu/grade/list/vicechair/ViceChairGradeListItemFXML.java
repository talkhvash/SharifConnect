package client.main.java.view.FXML.edu.grade.list.vicechair;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import client.main.java.db.ModelLoader;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;
import shared.model.edu.user.User;

public class ViceChairGradeListItemFXML {

    @FXML
    private Label nameLabel,
            objectionLabel,
            answerLabel,
            numberLabel;

    public void initialize(Grade grade, boolean studentView) {
        if (studentView) {
            Course course = ModelLoader.getLoader().loadCourse(grade.getCourseId());
            if (course != null) nameLabel.setText(course.getName());
        } else {
            User user = ModelLoader.getLoader().loadUser(grade.getStudentId());
            if (user != null) nameLabel.setText(user.getName());
        }

        if (grade.isFinalizedObjection()) {
            objectionLabel.setText(grade.getAnswer());
            if (grade.isFinalizedAnswer()) {
                answerLabel.setText(grade.getAnswer());
            }
        }
        numberLabel.setText(grade.getNumber() + "");

    }


}
