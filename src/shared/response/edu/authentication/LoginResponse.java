package shared.response.edu.authentication;

import shared.model.edu.user.User;
import shared.response.Response;
import shared.response.ResponseHandler;

public class LoginResponse extends Response {
    private final boolean result;
    private final boolean changePassword;
    private final String authToken;
    private final User loggedInUser;
    private final LoginFormError formError;

    public LoginResponse(boolean result,
                         boolean changePassword,
                         String authToken,
                         User loggedInUser,
                         LoginFormError formError) {
        this.result = result;
        this.changePassword = changePassword;
        this.authToken = authToken;
        this.loggedInUser = loggedInUser;
        this.formError = formError;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.login(this);
    }

    // getters and setters


    public boolean isResult() {
        return result;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public String getAuthToken() {
        return authToken;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public LoginFormError getFormError() {
        return formError;
    }
}
