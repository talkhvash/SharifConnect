package client.main.java.view.FXML.edu.course.exam;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import shared.model.edu.course.Course;

import java.util.LinkedList;

public class ExamListFXML{
    @FXML
    private VBox vBox;

    public void refresh(LinkedList<Course> list) {
        vBox.getChildren().clear();
        for (Course item : list) {
            ExamListItem temp = new ExamListItem();
            temp.getFXML().initialize(item);
            vBox.getChildren().add(temp.getHBox());
        }
    }
}
