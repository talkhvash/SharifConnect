package client.main.java.view.FXML.edu.user.list.professor;

import client.main.java.view.GUIController;
import shared.request.edu.user.list.DeleteProfessorRequest;
import shared.request.edu.user.list.SetViceChairRequest;
import shared.request.edu.user.view.neww.ViewNewProfessorRequest;
import shared.request.edu.user.view.edit.ViewProfessorRequest;

public class ProfessorListListener {

    public void listen(String type, Integer professorId) {
        switch (type) {
            case "edit":
                GUIController.getController().getListener().listen(new ViewProfessorRequest(professorId));
                break;
            case "new":
                GUIController.getController().getListener().listen(new ViewNewProfessorRequest());
                break;
            case "delete":
                GUIController.getController().getListener().listen(new DeleteProfessorRequest(professorId));
                break;
            case "setViceChair":
                GUIController.getController().getListener().listen(new SetViceChairRequest(professorId));
        }
    }
}
