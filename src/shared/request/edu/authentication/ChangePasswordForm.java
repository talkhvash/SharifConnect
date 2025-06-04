package shared.request.edu.authentication;

public class ChangePasswordForm {
    private final String lastPassword;
    private final String newPassword;

    public ChangePasswordForm(String lastPassword, String newPassword) {
        this.lastPassword = lastPassword;
        this.newPassword = newPassword;
    }

    // getters and setters
    public String getLastPassword() {
        return lastPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
