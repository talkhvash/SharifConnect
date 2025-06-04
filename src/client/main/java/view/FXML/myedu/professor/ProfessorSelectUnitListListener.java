package client.main.java.view.FXML.myedu.professor;

import client.main.java.view.GUIController;
import shared.request.FilterStudentForm;
import shared.request.myedu.professor.RefreshProfessorSUListRequest;
import shared.request.myedu.professor.SetStudentSUTimeRequest;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ProfessorSelectUnitListListener {
    public void listen(LinkedList<Integer> studentId, LocalDateTime dt) {
        GUIController.getController().getListener().listen(new SetStudentSUTimeRequest(studentId, dt));
    }

    public void listen(FilterStudentForm form) {
        GUIController.getController().startLoop(
                () -> GUIController.getController().getListener().listen(
                        new RefreshProfessorSUListRequest(form)
                ));
    }
}
