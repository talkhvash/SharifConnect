package client.main.java.view.FXML.edu.authentication.login;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import client.main.java.util.FormValidation;
import shared.model.edu.Captcha;
import shared.request.edu.authentication.LoginForm;
import shared.response.edu.authentication.LoginFormError;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;

public class LoginPageFXML {
    private final LoginPageListener listener = new LoginPageListener();

    @FXML
    private TextField usernameField,
            passwordField,
            captchaPasswordField;
    @FXML
    private Label usernameErrorLabel,
            passwordErrorLabel,
            captchaErrorLabel;
    @FXML
    private ImageView captchaImage;

    private Captcha captcha;

    public void refresh() {
        captcha = new Captcha();
        captcha.setId(-1);
        changeCaptcha();
    }

    public void setError(LoginFormError error) {
        // error
        captchaErrorLabel.setText(error.getCaptchaError());
        usernameErrorLabel.setText(error.getUsernameError());
        passwordErrorLabel.setText(error.getPasswordError());

        changeCaptcha();
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
        String image = captcha.getImageBase64();
        try {
            System.out.println(image);
            byte[] imageByte = ImageUtil.decodeFromBase64(image);
            captchaImage.setImage(new Image(new ByteArrayInputStream(imageByte)));
        } catch (Exception e) {
            changeCaptcha();
        }
    }

    private boolean error() {
        passwordErrorLabel.setText("");
        usernameErrorLabel.setText("");
        FormValidation validation = new FormValidation();
        return validation.isEmpty(usernameField, usernameErrorLabel)
                | validation.isEmpty(passwordField, passwordErrorLabel);
    }

    private LoginForm getForm() {
        return new LoginForm(
                usernameField.getText(),
                passwordField.getText(),
                captcha.getId(),
                captchaPasswordField.getText()
        );
    }

    @FXML
    private void login() {
        if (!error()) listener.listen("login", getForm());
    }

    @FXML
    private void changeCaptcha() {
        listener.listen("changeCaptcha", getForm());
    }

}
