package shared.response.edu.user.view.newuser;

import shared.response.Response;
import shared.response.ResponseHandler;
import shared.response.edu.user.view.ViewProfessorErrorForm;

public class NewProfessorResponse extends Response {
    private boolean result;
    private int professorId;
    private ViewProfessorErrorForm errorForm;

    @Override
    public void handle(ResponseHandler handler) {
        handler.newProfessor(this);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public ViewProfessorErrorForm getErrorForm() {
        return errorForm;
    }

    public void setErrorForm(ViewProfessorErrorForm errorForm) {
        this.errorForm = errorForm;
    }
}
