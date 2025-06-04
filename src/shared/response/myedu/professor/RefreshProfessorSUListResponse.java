package shared.response.myedu.professor;

import shared.model.edu.user.Student;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshProfessorSUListResponse extends Response {
    private LinkedList<Student> studentsList;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshProfessorSUList(this);
    }

    public LinkedList<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(LinkedList<Student> studentsList) {
        this.studentsList = studentsList;
    }
}
