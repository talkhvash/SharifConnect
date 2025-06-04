package shared.response.edu.user.profile;

import shared.model.edu.user.User;
import shared.response.Response;
import shared.response.ResponseHandler;

public class EditProfileResponse extends Response {
    private User user;
    private EditProfileErrorForm errorForm;

    @Override
    public void handle(ResponseHandler handler) {
        handler.editProfile(this);
    }

    // getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EditProfileErrorForm getErrorForm() {
        return errorForm;
    }

    public void setErrorForm(EditProfileErrorForm errorForm) {
        this.errorForm = errorForm;
    }

}
