package client.main.java.view.FXML.edu.authentication.login;

import client.main.java.view.GUIController;
import shared.request.edu.authentication.ChangeCaptchaRequest;
import shared.request.edu.authentication.LoginForm;
import shared.request.edu.authentication.LoginRequest;

public class LoginPageListener {

    public void listen(String eventType, LoginForm form) {
        switch (eventType) {
            case "login":
                GUIController.getController().getListener().listen(new LoginRequest(form));
                System.out.println(54545);
                break;
            case "changeCaptcha":
                GUIController.getController().getListener().listen(new ChangeCaptchaRequest(form.getCaptchaId()));
                System.out.println(645);
                break;
        }
    }

}
