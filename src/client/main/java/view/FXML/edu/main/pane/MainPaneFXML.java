package client.main.java.view.FXML.edu.main.pane;

import client.main.java.constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import client.main.java.db.ModelLoader;
import shared.config.Config;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.model.edu.user.User;
import shared.util.ImageUtil;


import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

public class MainPaneFXML {
    @FXML
    private Label nameLabel,
            emailLabel,
            stateLabel,
            signupPermissionLabel,
            supervisorNameLabel,
            signupTimeLabel;
    @FXML
    private VBox studentBox1,
            studentBox2;
    @FXML
    private ImageView profilePicture;

    public void refresh(User user) {
        nameLabel.setText(user.getName());
        emailLabel.setText(user.getEmail());

        if (user instanceof Professor) {
            studentBox1.setVisible(false);
            studentBox2.setVisible(false);
        } else {
            Student student = (Student) user;

            LocalDateTime time = student.getSelectUnitTime();
            signupTimeLabel.setText(time.getYear() + "/" + time.getMonthValue() + "/" + time.getDayOfMonth() + " "
                    + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
            if (student.isSelectUnitPermission()) {
                signupPermissionLabel.setText("مجاز");
            } else {
                signupPermissionLabel.setText("غیر مجاز");
            }
            Professor supervisor = (Professor) ModelLoader.getLoader().loadUser(student.getSupervisorId());
            if (supervisor != null) supervisorNameLabel.setText(supervisor.getName());
            stateLabel.setText(student.getState().toString());
        }

        setPicture(user.getImageBase64());
    }

    private void setPicture(String imageBase64) {
        try {
            byte[] bytes = ImageUtil.decodeFromBase64(imageBase64);
            profilePicture.setImage(new Image(new ByteArrayInputStream(bytes)));
        } catch (Exception e) {
            String defaultPicture = new Config(Constants.CONFIG).getProperty(String.class, "defaultPicture");
            byte[] bytes = ImageUtil.decodeFromBase64(defaultPicture);
            profilePicture.setImage(new Image(new ByteArrayInputStream(bytes)));
        }

    }
}
