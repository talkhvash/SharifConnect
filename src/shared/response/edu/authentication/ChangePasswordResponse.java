package shared.response.edu.authentication;

import shared.response.Response;
import shared.response.ResponseHandler;

public class ChangePasswordResponse extends Response {
    private final boolean result;
    private final ChangePasswordErrorForm form;

    public ChangePasswordResponse(boolean result, ChangePasswordErrorForm form) {
        this.result = result;
        this.form = form;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.changePassword(this);
    }

    // getters and setters
    public boolean isResult() {
        return result;
    }

    public ChangePasswordErrorForm getForm() {
        return form;
    }

}
