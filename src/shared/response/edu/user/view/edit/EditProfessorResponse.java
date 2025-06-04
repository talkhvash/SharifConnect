package shared.response.edu.user.view.edit;

import shared.response.Response;
import shared.response.ResponseHandler;
import shared.response.edu.user.view.ViewProfessorErrorForm;

public class EditProfessorResponse extends Response {
    private ViewProfessorErrorForm errorForm;

    @Override
    public void handle(ResponseHandler handler) {
        handler.editProfessor(this);
    }

    // getters and setters

    public ViewProfessorErrorForm getErrorForm() {
        return errorForm;
    }

    public void setErrorForm(ViewProfessorErrorForm errorForm) {
        this.errorForm = errorForm;
    }

}
