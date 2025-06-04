package shared.request.edu.user.view.neww;

import shared.model.edu.user.Student;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.newuser.NewStudentResponse;

public class NewStudentRequest extends Request {
    private final Student student;

    public NewStudentRequest(Student student) {
        this.student = student;
    }

    @Override
    public NewStudentResponse handle(RequestHandler handler) {
        return handler.newStudent(student);
    }

    public Student getStudent() {
        return student;
    }

}
