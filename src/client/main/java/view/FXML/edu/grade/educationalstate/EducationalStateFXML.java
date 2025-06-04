package client.main.java.view.FXML.edu.grade.educationalstate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shared.model.edu.course.Grade;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;

import java.util.LinkedList;

public class EducationalStateFXML {
    private final EducationalStateListener listener = new EducationalStateListener();

    @FXML
    private VBox box;
    @FXML
    private HBox professorBox;
    @FXML
    private Label gradeLabel,
            unitLabel;
    @FXML
    private TextField studentNameField,
            studentIdField;

    public void initialize(User viewer) {
        if (viewer instanceof Student) {
            professorBox.setVisible(false);
            listener.listen(viewer.getId(), null);
        } else if (viewer instanceof Professor) {
            professorBox.setVisible(true);
        }
    }

    public void refresh(LinkedList<Grade> list, Student student) {
        box.getChildren().clear();
        if (student == null) {
            gradeLabel.setText("");
            unitLabel.setText("");
            return;
        }

        gradeLabel.setText(student.getGrade() + "");
        unitLabel.setText(student.getPassedUnit() + "");
        for (Grade item : list) {
            EducationalStateItem temp = new EducationalStateItem();
            temp.getFXML().initialize(item);
            box.getChildren().add(temp.getHBox());
        }
    }

    @FXML
    private void showByName() {
        listener.listen(
                null,
                studentNameField.getText());
    }

    @FXML
    private void showById() {
        try {
            listener.listen(
                    Integer.parseInt(studentIdField.getText()),
                    null);
        } catch (NumberFormatException ignored) {}
    }

}
