package client.main.java.view.FXML.edu.user.view.professor;

import client.main.java.view.GUIController;
import shared.model.edu.user.Professor;
import shared.request.edu.user.view.edit.EditProfessorRequest;
import shared.request.edu.user.view.neww.NewProfessorRequest;

public class ProfessorViewListener {

    public void listen(String type, Professor professor, Integer professorId) {
        switch (type) {
            case "edit":
                GUIController.getController().getListener().listen(
                        new EditProfessorRequest(professorId, professor));
                break;
            case "new":
                GUIController.getController().getListener().listen(new NewProfessorRequest(professor));
                break;
        }
    }
}
