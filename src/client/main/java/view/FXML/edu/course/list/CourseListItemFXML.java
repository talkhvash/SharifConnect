package client.main.java.view.FXML.edu.course.list;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import client.main.java.db.ModelLoader;
import shared.model.edu.course.Course;
import shared.model.edu.Department;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;

public class CourseListItemFXML {
    private final CourseListListener listener = new CourseListListener();
    @FXML
    private Label nameLabel,
            numberLabel,
            departmentLabel,
            degreeLabel,
            unitLabel;
    @FXML
    private Button deleteButton,
            editButton;

    private Integer courseId;
    public void initialize(User viewer, Course course) {
        courseId = course.getId();

        nameLabel.setText(course.getName());

        numberLabel.setText(course.getNumber() + "");

        Department department = ModelLoader.getLoader().loadDepartment(course.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());

        degreeLabel.setText(course.getDegree().toString());

        unitLabel.setText(course.getUnit().toString() + "");

        if (viewer instanceof Student) {
            deleteButton.setVisible(false);
            editButton.setVisible(false);
        } else if (viewer instanceof Professor) {
            Professor professor = (Professor) viewer;
            deleteButton.setVisible(professor.isViceChair());
            editButton.setVisible(professor.isViceChair());
        }
    }

    @FXML
    private void editCourse() {
        listener.listen("edit", courseId);
    }

    @FXML
    private void deleteCourse() {
        listener.listen("delete", courseId);
    }
}
