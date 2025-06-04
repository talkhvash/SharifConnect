package client.main.java.view.FXML.edu.grade.list.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import client.main.java.db.ModelLoader;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;


public class StudentGradeListItemFXML {
    private final StudentGradeListListener listener = new StudentGradeListListener();

    @FXML
    private Label nameLabel,
            answerLabel,
            gradeLabel,
            objectionLabel;
    @FXML
    private Button assignButton;
    @FXML
    private HBox box;
    @FXML
    private TextField objectionField;

    private Integer id;

    public void initialize(Grade grade) {
        this.id = grade.getId();
        Course course = ModelLoader.getLoader().loadCourse(grade.getCourseId());
        if (course != null) nameLabel.setText(course.getName());

        if (grade.isFinalizedObjection()) {
            box.getChildren().remove(objectionField);
            box.getChildren().remove(assignButton);
            objectionLabel.setText(grade.getObjection());
        } else {
            box.getChildren().remove(objectionLabel);
            objectionField.setText(grade.getObjection());
        }

        answerLabel.setText(grade.getAnswer());
        gradeLabel.setText(grade.getNumber() + "");

        objectionField.setOnKeyPressed(event -> {
            listener.listen(objectionField.getText(), id, false);
        });

    }

    @FXML
    private void assign() {
        listener.listen(objectionField.getText(), id, true);
    }


}
