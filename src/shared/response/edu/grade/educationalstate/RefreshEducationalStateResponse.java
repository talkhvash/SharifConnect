package shared.response.edu.grade.educationalstate;

import shared.model.edu.course.Grade;
import shared.model.edu.user.Student;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshEducationalStateResponse extends Response {
    private LinkedList<Grade> list;
    private Student student;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshEducationalState(this);
    }

    public LinkedList<Grade> getList() {
        return list;
    }

    public void setList(LinkedList<Grade> list) {
        this.list = list;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
