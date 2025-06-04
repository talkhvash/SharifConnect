package shared.request.edu.user.profile;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.profile.ViewProfileResponse;

public class ViewProfileRequest extends Request {

    @Override
    public ViewProfileResponse handle(RequestHandler handler) {
        return handler.viewProfile();
    }

}
