package shared.response.edu.user.view.edit;

import shared.model.edu.user.Student;
import shared.response.Response;
import shared.response.ResponseHandler;

public class ViewStudentResponse extends Response {
    private Student student;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewStudent(this);
    }

    // getters and setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
