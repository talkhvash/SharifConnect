package shared.response.edu.user.profile;

public class EditProfileErrorForm {
    private String phoneNumberError = "",
            emailError = "";

    // getters and setters

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }
}
