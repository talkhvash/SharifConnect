package client.main.java.view.FXML.edu.user.list.professor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import client.main.java.db.ModelLoader;
import shared.model.edu.Department;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;

public class ProfessorListItemFXML {
    private final ProfessorListListener listener = new ProfessorListListener();
    @FXML
    private Label nameLabel,
            departmentLabel,
            levelLabel,
            roomNumberLabel;
    @FXML
    private CheckBox viceChairCheckBox;
    @FXML
    private HBox chairmanBox,
            box;
    @FXML
    private Button deleteButton;

    private Professor professor;

    public void initialize(User viewer, Professor professor) {
        this.professor = professor;

        nameLabel.setText(professor.getName());
        Department department = ModelLoader.getLoader().loadDepartment(professor.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        levelLabel.setText(professor.getLevel().toString());
        roomNumberLabel.setText(professor.getRoomNumber() + "");

        viceChairCheckBox.setSelected(professor.isViceChair());

        if (viewer instanceof Student) {
            box.getChildren().remove(chairmanBox);
        } else if (viewer instanceof Professor) {
            Professor tempViewer = (Professor) viewer;
            if (professor.isChairman()) {
                chairmanBox.getChildren().remove(deleteButton);
                chairmanBox.getChildren().remove(viceChairCheckBox);
            }
            chairmanBox.setVisible(tempViewer.isChairman());
        }

    }

    @FXML
    private void edit() {
        listener.listen("edit", professor.getId());
    }

    @FXML
    private void delete() {
        listener.listen("delete", professor.getId());
    }

    @FXML
    private void viceChair() {
        if (professor.isViceChair()) {
            viceChairCheckBox.setSelected(true);
        } else {
            listener.listen("setViceChair", professor.getId());
        }
    }

}
