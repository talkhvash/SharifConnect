package shared.request.edu.authentication;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.Response;

public class LoginRequest extends Request {
    private final LoginForm form;

    public LoginRequest(LoginForm form) {
        this.form = form;
    }

    @Override
    public Response handle(RequestHandler handler) {
        return handler.login(form);
    }

    // getters and setters
    public LoginForm getLoginForm() {
        return form;
    }

}
