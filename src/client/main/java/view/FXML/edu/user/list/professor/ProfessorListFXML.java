package client.main.java.view.FXML.edu.user.list.professor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;

import java.util.LinkedList;


public class ProfessorListFXML {
    private final ProfessorListListener listener = new ProfessorListListener();
    @FXML
    private Button newButton;
    @FXML
    private VBox vBox;

    public void refresh(User viewer, LinkedList<Professor> list) {
        if (viewer instanceof Student) {
            newButton.setVisible(false);
        } else if (viewer instanceof Professor) {
            Professor professor = (Professor) viewer;
            if (!professor.isChairman()) {
                newButton.setVisible(false);
            }
        }

        vBox.getChildren().clear();
        for (Professor item : list) {
            ProfessorListItem temp = new ProfessorListItem();
            temp.getFXML().initialize(viewer, item);
            vBox.getChildren().add(temp.getHBox());
        }

    }

    @FXML
    private void newProfessor() {
        listener.listen("new", null);
    }
}
