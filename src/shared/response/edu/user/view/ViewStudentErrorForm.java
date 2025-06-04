package shared.response.edu.user.view;

public class ViewStudentErrorForm {
    private String firstNameError = "",
            lastNameError = "",
            nationalNumberError = "",
            emailError = "",
            yearError = "",
            phoneNumberError = "",
            degreeError = "",
            supervisorError = "",
            randError = "";

    // getters and setters
    public String getRandError() {
        return randError;
    }

    public void setRandError(String randError) {
        this.randError = randError;
    }

    public String getFirstNameError() {
        return firstNameError;
    }

    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getNationalNumberError() {
        return nationalNumberError;
    }

    public void setNationalNumberError(String nationalNumberError) {
        this.nationalNumberError = nationalNumberError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getYearError() {
        return yearError;
    }

    public void setYearError(String yearError) {
        this.yearError = yearError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getDegreeError() {
        return degreeError;
    }

    public void setDegreeError(String degreeError) {
        this.degreeError = degreeError;
    }

    public String getSupervisorError() {
        return supervisorError;
    }

    public void setSupervisorError(String supervisorError) {
        this.supervisorError = supervisorError;
    }

}
