package shared.response.edu.authentication;

public class ChangePasswordErrorForm {
    private String lastPasswordError = "",
            newPasswordError = "";

    // getters and setters
    public String getLastPasswordError() {
        return lastPasswordError;
    }

    public void setLastPasswordError(String lastPasswordError) {
        this.lastPasswordError = lastPasswordError;
    }

    public String getNewPasswordError() {
        return newPasswordError;
    }

    public void setNewPasswordError(String newPasswordError) {
        this.newPasswordError = newPasswordError;
    }
}
