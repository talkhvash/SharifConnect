package server.main.java.controller;

import client.main.java.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.main.java.constants.Constants;
import server.main.java.db.DB;
import server.main.java.util.AuthToken;
import shared.config.Config;
import shared.model.edu.Captcha;
import shared.model.edu.enums.StudentState;
import shared.model.edu.user.Student;
import shared.request.edu.authentication.ChangePasswordForm;
import shared.request.edu.authentication.LoginForm;
import shared.response.edu.authentication.*;
import shared.model.edu.user.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;


public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);

    private final DB db = DB.getDB();

    private final static AuthToken generator = new AuthToken();
    private Setter<String> authTokenSetter;
    private Setter<Integer> loggedInUserIdSetter;

    public LoginResponse login(LoginForm form) {
        String password = form.getPassword();
        String captchaPassword = form.getCaptchaPassword();
        String username = form.getUsername();
        int captchaId = form.getCaptchaId();

        LoginFormError errorForm = new LoginFormError();

        // captcha validation
        try {
            Captcha captcha = db.getCaptcha(captchaId);
            if (!captcha.getPassword().equals(captchaPassword)) {
                errorForm.setCaptchaError("نامعتبر");
                return new LoginResponse(false, false, null, null, errorForm);
            }
        } catch (Exception e) {
            log.error("while loading captcha");
        }

        // username validation
        User user = null;
        try {
            user = db.getUser(Integer.parseInt(username));
            if (user == null) {
                errorForm.setUsernameError("شناسه کاربری یافت نشد.");
                return new LoginResponse(false, false, null, null, errorForm);
            }

            // password validation
            if (!user.getPassword().equals(password)) {
                errorForm.setPasswordError("رمز عبور اشباه است.");
                log.warn("unsuccessful try to login to user " + user.getId());
                return new LoginResponse(false, false, null, null, errorForm);
            }

            if (user instanceof Student) {
                if (((Student) user).getState() == StudentState.WITHDRAW) {
                    errorForm.setUsernameError("شما از تحصیل انصراف داده اید.");
                    log.warn("");
                    return new LoginResponse(false, false, null, null, errorForm);
                }
            }
        } catch (Exception e) {
            log.error("while loading user");
        }

        // authentication token
        String token = generator.getToken();
        authTokenSetter.set(token);
        assert user != null;
        loggedInUserIdSetter.set(user.getId());
        log.info("set token to user " + user.getId());

        // should change password or not?
        boolean changePassword;
        int maxDistance = new Config(Constants.CONFIG).getProperty(Integer.class, "changePasswordMinutes");
        long distance = ChronoUnit.MINUTES.between(LocalDateTime.now(), user.getLastInterTime());
        if (distance > maxDistance) {
            changePassword = true;
            log.info("user " + user.getId() + " should change his password");
        } else {
            changePassword = false;
            user.setLastInterTime(LocalDateTime.now());
            try {
                db.updateUser(user);
                log.info("user " + user.getId() + " logged in successfully");
            } catch (Exception e) {
                log.error("while updating user: " + user.getId());
            }
        }

        return new LoginResponse(
                true,
                changePassword,
                token,
                user,
                null);
    }

    public ChangePasswordResponse changePassword(ChangePasswordForm form, int userId) {
        String lastPassword = form.getLastPassword();
        String newPassword = form.getNewPassword();

        ChangePasswordErrorForm errorForm = new ChangePasswordErrorForm();

        User user;
        try {
            user = db.getUser(userId);

            // last password validation
            if (!user.getPassword().equals(lastPassword)) {
                errorForm.setLastPasswordError("رمز عبور قبلی اشتباه است.");
                log.warn("user " + user.getId() + " unsuccessfully tried to change his password");
                return new ChangePasswordResponse(false, errorForm);
            }

            // new password validation
            if (user.getPassword().equals(newPassword)) {
                errorForm.setNewPasswordError("رمز عبور جدید باید با قبلی متفاوت باشد.");
                log.warn("user " + user.getId() + " unsuccessfully tried to change his password");
                return new ChangePasswordResponse(false, errorForm);
            }

        } catch (Exception e) {
            log.error("error while loading user: " + userId);
            return new ChangePasswordResponse(false, errorForm);
        }

        try {
            db.updateUser(user);
            user.setPassword(newPassword);
            user.setLastInterTime(LocalDateTime.now());
        } catch (Exception e) {
            log.error("while updating user");
        }

        log.info("user " + user.getId() + " logged in");
        return new ChangePasswordResponse(true, null);
    }

    public ChangeCaptchaResponse changeCaptcha(int captchaId) {
        Random random = new Random();
        int newCaptchaId = -1;
        while (newCaptchaId + 1 == captchaId) {
            int captchaCount = new Config(Constants.CONFIG).getProperty(Integer.class, "captchaCount");
            newCaptchaId = random.nextInt(captchaCount + 1);
        }
        Captcha captcha = null;
        try {
            captcha = db.getCaptcha(newCaptchaId);
            System.out.println(captcha.getImageBase64());
            captcha.setPassword(null);
        } catch (Exception e) {
            log.error("while loading captcha");
        }
        return new ChangeCaptchaResponse(captcha);
    }

    // setters
    public void setAuthTokenSetter(Setter<String> authTokenSetter) {
        this.authTokenSetter = authTokenSetter;
    }

    public void setLoggedInUserIdSetter(Setter<Integer> loggedInUserIdSetter) {
        this.loggedInUserIdSetter = loggedInUserIdSetter;
    }

}
