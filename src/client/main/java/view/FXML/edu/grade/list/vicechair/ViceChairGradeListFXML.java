package client.main.java.view.FXML.edu.grade.list.vicechair;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;

import java.util.HashMap;
import java.util.LinkedList;

public class ViceChairGradeListFXML {
    private ViceChairGradeListListener listener = new ViceChairGradeListListener();

    @FXML
    private Label nameHeader;
    @FXML
    private VBox box;
    @FXML
    private TextField professorField,
            studentIdField,
            studentNameField;
    @FXML
    private ChoiceBox<Course> courseBox;

    public void initialize(LinkedList<Course> list) {
        courseBox.setItems(FXCollections.observableList(list));
    }

    public void refresh(LinkedList<Grade> list, boolean studentView) {
        box.getChildren().clear();
        if (studentView) {
            nameHeader.setText("نام درس");
        } else {
            nameHeader.setText("نام دانشجو");
        }
        for (Grade grade : list) {
            ViceChairGradeListItem add = new ViceChairGradeListItem();
            add.getFXML().initialize(grade, studentView);
            box.getChildren().add(add.getHBox());
        }
    }

    public void refresh(HashMap<Course, LinkedList<Grade>> map) {
        nameHeader.setText("نام دانشجو");
        box.getChildren().clear();
        for (Course item : map.keySet()) {
            box.getChildren().add(courseInfoBox(item));
            for (Grade grade : map.get(item)) {
                ViceChairGradeListItem add = new ViceChairGradeListItem();
                add.getFXML().initialize(grade, false);
                box.getChildren().add(add.getHBox());
            }
        }

    }

    private HBox courseInfoBox(Course course) {
        HBox hBox = new HBox();
        hBox.setPrefSize(-1, -1);
        hBox.setSpacing(5);
        hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        hBox.getChildren().add(new Label("نام درس:"));
        hBox.getChildren().add(new Label(course.getName()));
        hBox.getChildren().add(new Label("معدل درس:"));
        hBox.getChildren().add(new Label(course.getWithFailedAverage() + ""));
        hBox.getChildren().add(new Label("تعداد افراد مردود:"));
        hBox.getChildren().add(new Label(course.getFailStudentsCount() + ""));
        hBox.getChildren().add(new Label("تعداد افرود قبول شده:"));
        hBox.getChildren().add(new Label(course.getPassStudentsCount() + ""));
        hBox.getChildren().add(new Label("معدل درس بدون در نظر گرفتن افراد مردود:"));
        hBox.getChildren().add(new Label(course.getWithoutFailedAverage() + ""));
        return hBox;
    }

    @FXML
    private void showProfessor() {
        listener.listen(null, null, professorField.getText(), null);
    }
    @FXML
    private void showCourse() {
        listener.listen(courseBox.getValue().getId(), null, null, null);
    }
    @FXML
    private void showStudentId() {
        try {
            Integer.parseInt(studentIdField.getText());
            listener.listen(null, null, null, Integer.parseInt(studentIdField.getText()));
        } catch (Exception ignored) {}

    }
    @FXML
    private void showStudentName() {
        listener.listen(null, studentNameField.getText(), null, null);
    }

}
