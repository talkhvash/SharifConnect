package shared.request.edu.user.view.edit;

import shared.model.edu.user.Student;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.edit.EditStudentResponse;

public class EditStudentRequest extends Request {
    private final int studentId;
    private final Student student;

    public EditStudentRequest(int studentId, Student student) {
        this.studentId = studentId;
        this.student = student;
    }

    @Override
    public EditStudentResponse handle(RequestHandler handler) {
        return handler.editStudent(student, studentId);
    }

    // getters and setters

    public int getStudentId() {
        return studentId;
    }

    public Student getStudent() {
        return student;
    }

}
