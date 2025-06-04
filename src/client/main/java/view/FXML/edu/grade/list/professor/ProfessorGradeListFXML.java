package client.main.java.view.FXML.edu.grade.list.professor;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;

import java.util.LinkedList;

public class ProfessorGradeListFXML {
    private final ProfessorGradeListListener listener = new ProfessorGradeListListener();

    @FXML
    private Button temporaryButton,
            finalButton;
    @FXML
    private VBox box;
    @FXML
    private ChoiceBox<Course> courseBox;


    public void initialize(LinkedList<Course> courses) {
        courseBox.setItems(FXCollections.observableList(courses));
        courseBox.setOnAction(event -> listener.listen(courseBox.getValue()));
        finalButton.setVisible(false);
        temporaryButton.setVisible(false);
    }

    private Integer courseId;
    private LinkedList<ProfessorGradeListItemFXML> FXMLs = new LinkedList<>();
    public void refresh(LinkedList<Grade> list, Boolean finalized, Integer id) {
        courseId = id;
        if (finalized == null) {
            temporaryButton.setVisible(true);
            finalButton.setVisible(true);
        } else if (finalized) {
            temporaryButton.setVisible(false);
            finalButton.setVisible(false);
        } else {
            temporaryButton.setVisible(false);
            finalButton.setVisible(true);
        }

        box.getChildren().clear();
        FXMLs.clear();
        for (Grade grade : list) {
            ProfessorGradeListItem item = new ProfessorGradeListItem();
            FXMLs.add(item.getFXML());
            item.getFXML().initialize(grade);
            box.getChildren().add(item.getHBox());
        }
    }

    @FXML
    private void finalizing() {
        for (ProfessorGradeListItemFXML item : FXMLs) if (!item.isValid()) return;
        listener.listen(courseId, true);
    }

    @FXML
    private void temporary() {
        for (ProfessorGradeListItemFXML item : FXMLs) if (!item.isValid()) return;
        listener.listen(courseId, false);
    }

}
