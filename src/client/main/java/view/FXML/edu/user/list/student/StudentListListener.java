package client.main.java.view.FXML.edu.user.list.student;

import client.main.java.view.GUIController;
import shared.request.edu.user.list.RefreshStudentListRequest;

public class StudentListListener {
    public void listen(int partId) {
        GUIController.getController().startLoop(
                () -> GUIController.getController().getListener().listen(
                        new RefreshStudentListRequest(partId)
                ));
    }
}
