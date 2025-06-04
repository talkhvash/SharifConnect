package client.main.java.view.FXML.edu.authentication.changePassword;

import client.main.java.view.GUIController;
import shared.request.edu.authentication.ChangePasswordForm;
import shared.request.edu.authentication.ChangePasswordRequest;

public class ChangePasswordPageListener {

    public void listen(ChangePasswordForm form) {
        GUIController.getController().getListener().listen(new ChangePasswordRequest(form));
    }

}
