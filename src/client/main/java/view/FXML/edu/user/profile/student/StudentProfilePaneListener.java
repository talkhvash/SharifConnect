package client.main.java.view.FXML.edu.user.profile.student;

import client.main.java.view.GUIController;
import shared.request.edu.user.profile.EditProfileForm;
import shared.request.edu.user.profile.EditProfileRequest;

public class StudentProfilePaneListener {

    public void listen(EditProfileForm form) {
        GUIController.getController().getListener().listen(new EditProfileRequest(form));
    }

}
