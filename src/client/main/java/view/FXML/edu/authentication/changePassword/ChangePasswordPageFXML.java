package client.main.java.view.FXML.edu.authentication.changePassword;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import client.main.java.util.FormValidation;
import shared.request.edu.authentication.ChangePasswordForm;
import shared.response.edu.authentication.ChangePasswordErrorForm;

public class ChangePasswordPageFXML {
    private final ChangePasswordPageListener listener = new ChangePasswordPageListener();

    @FXML
    private PasswordField newPassword1Field,
            newPassword2Field,
            oldPasswordField;
    @FXML
    private Label newPassword1ErrorLabel,
            newPassword2ErrorLabel,
            oldPasswordErrorLabel;

    public void setError(ChangePasswordErrorForm errorForm) {
        oldPasswordErrorLabel.setText(errorForm.getLastPasswordError());
        newPassword1ErrorLabel.setText(errorForm.getNewPasswordError());
        newPassword2ErrorLabel.setText("");
    }

    private boolean error() {
        oldPasswordErrorLabel.setText("");
        newPassword1ErrorLabel.setText("");
        newPassword2ErrorLabel.setText("");

        FormValidation validation = new FormValidation();
        if (!newPassword1Field.getText().equals(newPassword2Field.getText()))
            newPassword2ErrorLabel.setText("تکرار رمز عبور اشتباه است.");

        return validation.isEmpty(oldPasswordField, oldPasswordErrorLabel)
                | validation.isEmpty(newPassword1Field, newPassword1ErrorLabel)
                | validation.equal(newPassword1Field, newPassword2Field, newPassword2ErrorLabel);
    }

    private ChangePasswordForm getForm() {
        return new ChangePasswordForm(
                oldPasswordField.getText()
                ,newPassword1Field.getText()
        );
    }

    @FXML
    private void save() {
        if (!error()) listener.listen(getForm());
    }

}
