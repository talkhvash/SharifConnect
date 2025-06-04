package client.main.java.view.FXML.edu.user.list.student;

import client.main.java.view.FXML.edu.user.profile.student.StudentProfilePane;
import client.main.java.view.GUIController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import shared.model.edu.user.Student;

import java.util.HashMap;
import java.util.LinkedList;


public class StudentListFXML {
    private final StudentListListener listener = new StudentListListener();

    @FXML
    private TextField idField;
    @FXML
    private VBox box;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab profileTab,
            listTab;

    public void refresh(LinkedList<Student> studentsList) {
        box.getChildren().clear();
        int index = 0;
        for (Student student : studentsList) {
            StudentListItem item = new StudentListItem();
            item.getFXML().initialize(student, index++);

            item.getFXML().setListener(id -> {
                Student selected = studentsList.get(id);
                StudentProfilePane pane = new StudentProfilePane();
                pane.getFXML().initialize(selected);
                profileTab.setContent(pane.getPane());
                tabPane.getSelectionModel().select(profileTab);
            });

            box.getChildren().add(item.getHBox());
        }
    }

    @FXML
    private void setRefresh() {
        try {
            int idPart = Integer.parseInt(idField.getText());
            if (idPart < 0) throw new Exception();
            listener.listen(idPart);
        } catch (Exception e) {
            if (idField.getText().equals("")) listener.listen(-1);
        }
    }


}
