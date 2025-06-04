package shared.response.edu.user.view.edit;

import shared.response.Response;
import shared.response.ResponseHandler;
import shared.response.edu.user.view.ViewStudentErrorForm;

public class EditStudentResponse extends Response {
    private ViewStudentErrorForm errorForm;

    @Override
    public void handle(ResponseHandler handler) {
        handler.editStudent(this);
    }

    // getters and setters

    public ViewStudentErrorForm getErrorForm() {
        return errorForm;
    }

    public void setErrorForm(ViewStudentErrorForm errorForm) {
        this.errorForm = errorForm;
    }

}
