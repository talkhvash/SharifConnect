package client.main.java.view.FXML.myedu.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import shared.model.edu.Department;
import shared.model.edu.course.Course;
import shared.model.edu.user.Student;
import shared.request.myedu.student.SelectUnitListSort;

import java.util.LinkedList;

public class StudentSelectUnitListFXML {
    private final StudentSelectUnitListListener listener = new StudentSelectUnitListListener();

    @FXML
    private VBox box1,
            box2;
    @FXML
    private Label errorLabel1,
            errorLabel2;
    @FXML
    private ChoiceBox<Department> departmentBox;
    @FXML
    private ChoiceBox<SelectUnitListSort> sortBox;

    @FXML
    private TabPane main;

    private final ObservableList<SelectUnitListSort> sortBoxItems = FXCollections.observableArrayList(
          SelectUnitListSort.SORT_BY_DEGREE,
          SelectUnitListSort.SORT_BY_NAME,
          SelectUnitListSort.SORT_BY_EXAM_DATE_TIME
    );

    private boolean tab = false;

    public void initialize(LinkedList<Department> departmentList) {
        departmentBox.setItems(FXCollections.observableList(departmentList));
        sortBox.setItems(sortBoxItems);
    }

    public void refresh(LinkedList<Course> list, Student viewer) {
        box1.getChildren().clear();
        box2.getChildren().clear();
        if (tab) {
            for (Course item : list) {
                StudentSelectUnitListItem temp = new StudentSelectUnitListItem();
                temp.getFXML().initialize(item, viewer);
                box1.getChildren().add(temp.getHBox());
            }
        } else {
            for (Course item : list) {
                StudentSelectUnitListItem temp = new StudentSelectUnitListItem();
                temp.getFXML().initialize(item, viewer);
                box2.getChildren().add(temp.getHBox());
            }
        }
        sortBox.setOnAction(event -> refreshRequest());
        departmentBox.setOnAction(event -> refreshRequest());
    }

    public void setError(String error) {
        if (tab) errorLabel1.setText(error);
        else errorLabel2.setText(error);
    }

    private void refreshRequest() {
        listener.listen(departmentBox.getValue(), sortBox.getValue() ,tab);
    }

    @FXML
    private void changeTab() {
        Tab selected = main.getSelectionModel().getSelectedItem();
        if (selected.getId().equals("tab1")) tab = true;
        else if (selected.getId().equals("tab2")) tab = false;
        refreshRequest();
    }
}
