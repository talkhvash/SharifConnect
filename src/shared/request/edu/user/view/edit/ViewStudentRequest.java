package shared.request.edu.user.view.edit;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.view.edit.ViewStudentResponse;

public class ViewStudentRequest extends Request {
    private final int studentId;

    public ViewStudentRequest(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public ViewStudentResponse handle(RequestHandler handler) {
        return handler.viewStudent(studentId);
    }

    // getters and setters

    public int getStudentId() {
        return studentId;
    }

}
