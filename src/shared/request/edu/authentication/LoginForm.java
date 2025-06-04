package shared.request.edu.authentication;

public class LoginForm {
    private final String username,
            password,
            captchaPassword;

    private final int captchaId;

    public LoginForm(String username,
                        String password,
                        int captchaId,
                        String captchaPassword) {
        this.username = username;
        this.password = password;
        this.captchaId = captchaId;
        this.captchaPassword = captchaPassword;
    }

    // getters and setters
    public int getCaptchaId(){
        return captchaId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCaptchaPassword() {
        return captchaPassword;
    }

}
