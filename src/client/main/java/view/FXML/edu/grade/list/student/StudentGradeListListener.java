package client.main.java.view.FXML.edu.grade.list.student;

import client.main.java.view.GUIController;
import shared.request.edu.grade.studentgradelist.ChangeObjectionRequest;

public class StudentGradeListListener {

    public void listen(String text, int id, boolean assign) {
        GUIController.getController().getListener().listen(new ChangeObjectionRequest(id, text, assign));
    }

}
