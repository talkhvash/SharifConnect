package client.main.java.view.FXML.myedu.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import server.main.java.constants.Constants;
import client.main.java.view.FXML.myedu.changegroup.ChangeGroupBox;
import shared.config.Config;
import shared.model.edu.course.Course;
import shared.model.edu.user.Student;

import java.time.format.DateTimeFormatter;

public class StudentSelectUnitListItemFXML {
    private final StudentSelectUnitListListener listener = new StudentSelectUnitListListener();
    @FXML
    private Label nameLabel,
            groupLabel,
            capacityLabel,
            degreeLabel,
            pishniazLabel,
            hamniazLabel,
            examDateTimeLabel;
    @FXML
    private Button signButton;
    @FXML
    private HBox box,
            addBox,
            deleteBox;

    private Integer id;
    private Course course;
    public void initialize(Course course, Student viewer) {
        id = course.getId();

        nameLabel.setText(course.getName());
        groupLabel.setText(course.getGroup() + "");
        capacityLabel.setText(course.getCapacityCount() + " (" + course.getStudentCount() + " )");
        degreeLabel.setText(course.getDegree().toString());

        if (course.getPishniazNumber() == null) pishniazLabel.setText("ندارد");
        else pishniazLabel.setText(course.getPishniazNumber() + "");

        if (course.getHamniazNumber() == null) hamniazLabel.setText("ندارد");
        else hamniazLabel.setText(course.getHamniazNumber() + "");

        // todo
        String pattern = new Config(Constants.CONFIG).getProperty(String.class, "dataPattern");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        examDateTimeLabel.setText(formatter.format(course.getExamDateTime()));

        if (viewer.getSignedCourseId().contains(course.getId())) {
            signButton.setText("یدون نشان کردن");
        } else {
            signButton.setText("نشان دار کردن");
        }
        if (viewer.getTermCoursesId().contains(course.getId())) {
            box.getChildren().remove(addBox);
        } else {
            box.getChildren().remove(deleteBox);
        }

    }

    @FXML
    private void changeSign() {
        listener.listen("changeSign", id, null);
    }
    @FXML
    private void add() {
        listener.listen("add", id, null);
    }
    @FXML
    private void changeGroup() {
        ChangeGroupBox box = new ChangeGroupBox(course.getOtherGroupId());
        box.setListener(x -> listener.listen("changeGroup", id, x));
    }
    @FXML
    private void delete() {
        listener.listen("delete", id, null);
    }
    @FXML
    private void demandCourse() {
        listener.listen("demand", id, null);
    }
}
