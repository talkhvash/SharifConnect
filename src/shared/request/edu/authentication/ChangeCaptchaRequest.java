package shared.request.edu.authentication;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.authentication.ChangeCaptchaResponse;

public class ChangeCaptchaRequest extends Request {
    public int lastCaptchaId;

    public ChangeCaptchaRequest() {

    }

    public ChangeCaptchaRequest(int lastCaptchaId) {
        this.lastCaptchaId = lastCaptchaId;
    }

    @Override
    public ChangeCaptchaResponse handle(RequestHandler handler) {
        return handler.changeCaptcha(lastCaptchaId);
    }

    public int getLastCaptchaId() {
        return lastCaptchaId;
    }

    public void setLastCaptchaId(int lastCaptchaId) {
        this.lastCaptchaId = lastCaptchaId;
    }
}
