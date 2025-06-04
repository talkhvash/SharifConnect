package client.main.java.view.FXML.edu.course.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import shared.model.edu.course.Course;
import shared.model.edu.enums.Degree;
import shared.model.edu.Department;
import shared.model.edu.course.Unit;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.request.FilterCourseForm;

import java.util.LinkedList;

public class CourseListFXML {
    private final CourseListListener listener = new CourseListListener();

    @FXML
    private ChoiceBox<Department> departmentBox;
    @FXML
    private ChoiceBox<Degree> degreeBox;
    @FXML
    private ChoiceBox<Unit> unitBox;
    @FXML
    private VBox vBox;
    @FXML
    private Button newCourseButton;

    private final ObservableList<Unit> unitBoxItems = FXCollections.observableArrayList(
            null,
            Unit.ONE,
            Unit.TOW,
            Unit.THREE,
            Unit.FOUR);
    private final ObservableList<Degree> degreeBoxItems = FXCollections.observableArrayList(
            null,
            Degree.UNDERGRADUATE,
            Degree.POSTGRADUATE,
            Degree.DOCTORATE);

    public void initialize(LinkedList<Department> departmentList) {
        newCourseButton.setOnAction(event -> refreshRequest());
        departmentBox.setOnAction(event -> refreshRequest());
        unitBox.setOnAction(event -> refreshRequest());

        departmentBox.setItems(FXCollections.observableList(departmentList));
        departmentBox.getItems().add(null);
        unitBox.setItems(unitBoxItems);
        degreeBox.setItems(degreeBoxItems);
    }

    public void refresh(User viewer, LinkedList<Course> list) {

        if (viewer instanceof Student) {
            newCourseButton.setVisible(false);
        } else if (viewer instanceof Professor) {
            Professor professor = (Professor) viewer;
            newCourseButton.setVisible(professor.isViceChair());
        }

        for (Course item : list) {
            CourseListItem temp = new CourseListItem();
            temp.getFXML().initialize(viewer, item);
            vBox.getChildren().add(temp.getHBox());
        }

    }

    @FXML
    private void newCourse() {
        listener.listen("new", null);
    }

    @FXML
    private void refreshRequest() {

        listener.listen(getFilter());
    }
    private FilterCourseForm getFilter() {
        FilterCourseForm form = new FilterCourseForm();
        form.setDepartment(departmentBox.getValue());
        form.setUnit(unitBox.getValue());
        form.setDegree(degreeBox.getValue());
        return form;
    }

}
