package shared.response.edu.user.view.newuser;

import shared.response.Response;
import shared.response.ResponseHandler;
import shared.response.edu.user.view.ViewStudentErrorForm;

public class NewStudentResponse extends Response {
    private boolean result;
    private int studentId;
    private ViewStudentErrorForm errorForm;

    @Override
    public void handle(ResponseHandler handler) {
        handler.newStudent(this);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ViewStudentErrorForm getErrorForm() {
        return errorForm;
    }

    public void setErrorForm(ViewStudentErrorForm errorForm) {
        this.errorForm = errorForm;
    }
}
