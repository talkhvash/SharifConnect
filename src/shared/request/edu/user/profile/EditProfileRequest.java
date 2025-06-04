package shared.request.edu.user.profile;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.profile.EditProfileResponse;

public class EditProfileRequest extends Request {
    private final EditProfileForm form;

    public EditProfileRequest(EditProfileForm form) {
        this.form = form;
    }

    @Override
    public EditProfileResponse handle(RequestHandler handler) {
        return handler.editProfile(form);
    }

    // getters and setters
    public EditProfileForm getForm() {
        return form;
    }

}
