package client.main.java.view.FXML.edu.grade.educationalstate;

import client.main.java.view.GUIController;
import shared.request.edu.grade.educationalstate.RefreshEducationalStateRequest;

public class EducationalStateListener {

    public void listen(Integer id, String name) {
        GUIController.getController().startLoop(
                () -> GUIController.getController().getListener().listen(
                        new RefreshEducationalStateRequest(name, id)));
    }

}
