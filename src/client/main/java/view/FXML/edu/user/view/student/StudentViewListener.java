package client.main.java.view.FXML.edu.user.view.student;

import client.main.java.view.GUIController;
import shared.model.edu.user.Student;
import shared.request.edu.user.view.edit.EditStudentRequest;
import shared.request.edu.user.view.neww.NewStudentRequest;

public class StudentViewListener {

    public void listen(String type, Student student, Integer studentId) {
        switch (type) {
            case "edit":
                GUIController.getController().getListener().listen(
                        new EditStudentRequest(studentId, student));
                break;
            case "new":
                GUIController.getController().getListener().listen(new NewStudentRequest(student));
                break;
        }
    }

}
