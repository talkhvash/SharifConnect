package shared.request.edu.authentication;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.Response;

public class ChangePasswordRequest extends Request {
    private final ChangePasswordForm form;

    public ChangePasswordRequest(ChangePasswordForm form) {
        this.form = form;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.changePassword(form);
    }

    // getters and setters
    public ChangePasswordForm getForm() {
        return form;
    }

}
