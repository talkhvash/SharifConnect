package shared.response.edu.authentication;

import shared.model.edu.Captcha;
import shared.response.Response;
import shared.response.ResponseHandler;

public class ChangeCaptchaResponse extends Response {
    private Captcha captcha;

    public ChangeCaptchaResponse(Captcha captcha) {
        this.captcha = captcha;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.changeCaptcha(this);
    }

    // setters and getters
    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }
}
