package client.main.java.view.FXML.edu.grade.list.student;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import shared.model.edu.course.Grade;

import java.util.LinkedList;

public class StudentGradeListFXML {

    @FXML
    private VBox box;

    public void initialize(LinkedList<Grade> list) {
        box.getChildren().clear();
        for (Grade item : list) {
            StudentGradeListItem temp = new StudentGradeListItem();
            temp.getFXML().initialize(item);
            box.getChildren().add(temp.getHBox());
        }
    }

}
