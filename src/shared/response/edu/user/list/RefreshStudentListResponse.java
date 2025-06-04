package shared.response.edu.user.list;

import shared.model.edu.user.Student;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.HashMap;
import java.util.LinkedList;

public class RefreshStudentListResponse extends Response {
    private final LinkedList<Student> studentsList;

    public RefreshStudentListResponse(LinkedList<Student> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshStudentList(studentsList);
    }

}
