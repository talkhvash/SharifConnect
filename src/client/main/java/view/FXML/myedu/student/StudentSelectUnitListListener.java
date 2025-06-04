package client.main.java.view.FXML.myedu.student;

import client.main.java.client.RequestListener;
import client.main.java.view.GUIController;
import shared.model.edu.Department;
import shared.request.myedu.student.RefreshStudentSUListRequest;
import shared.request.myedu.student.SelectUnitListSort;
import shared.request.myedu.student.StudentSUActionRequest;
import shared.request.myedu.student.StudentSUActionType;

public class StudentSelectUnitListListener {
    RequestListener listener = GUIController.getController().getListener();

    public void listen(String type, Integer courseId, Integer groupId) {
        StudentSUActionType actionType = StudentSUActionType.ADD_COURSE;;
        switch (type) {
            case "add":
                actionType = StudentSUActionType.ADD_COURSE;
                break;
            case "delete":
                actionType = StudentSUActionType.DELETE_COURSE;
                break;
            case "demand":
                actionType = StudentSUActionType.DEMAND_COURSE;
                break;
            case "changeGroup":
                actionType = StudentSUActionType.CHANGE_GROUP;
                break;
            case "changeSign":
                actionType = StudentSUActionType.CHANGE_SIGNED;
                break;

        }
        StudentSUActionRequest request = new StudentSUActionRequest(actionType, courseId, groupId);
        listener.listen(request);
    }

    public void listen(Department department, SelectUnitListSort sort, boolean tab) {
        listener.listen(new RefreshStudentSUListRequest(department, sort, tab));
    }
}
