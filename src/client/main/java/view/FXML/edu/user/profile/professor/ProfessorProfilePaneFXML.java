package client.main.java.view.FXML.edu.user.profile.professor;

import client.main.java.constants.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import client.main.java.db.ModelLoader;
import javafx.stage.Stage;
import shared.config.Config;
import shared.request.edu.user.profile.EditProfileForm;
import shared.response.edu.user.profile.EditProfileErrorForm;
import shared.model.edu.Department;
import shared.model.edu.user.Professor;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class ProfessorProfilePaneFXML {
    private final ProfessorProfilePaneListener listener = new ProfessorProfilePaneListener();

    @FXML
    private Label nameLabel,
            idNumberLabel,
            departmentLabel,
            nationalCodeLabel,
            roomNumberLabel,
            levelLabel,
            phoneNumberError,
            emailErrorLabel,
            pictureErrorLabel;
    @FXML
    private TextField phoneNumberField,
            emailField;
    @FXML
    private ImageView picture;
    @FXML
    private Button choosePictureButton,
            changeButton;

    private final FileChooser fileChooser = new FileChooser();

    private String imageBase64;

    public void offlineView() {
        emailErrorLabel.setText("");
        phoneNumberError.setText("");
        pictureErrorLabel.setText("");
        choosePictureButton.setDisable(true);
        changeButton.setDisable(true);
        phoneNumberField.setDisable(true);
        emailField.setDisable(true);
    }

    public void initialize(Professor professor) {
        nameLabel.setText(professor.getName());
        idNumberLabel.setText(professor.getId() + "");
        Department department = ModelLoader.getLoader().loadDepartment(professor.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        nationalCodeLabel.setText(professor.getNationalNumber() + "");
        roomNumberLabel.setText(professor.getRoomNumber() + "");
        levelLabel.setText(professor.getLevel().toString());
        phoneNumberField.setText(professor.getPhoneNumber() + "");
        emailField.setText(professor.getEmail());

        setPicture(imageBase64);
    }

    public void setError(EditProfileErrorForm errorForm) {
        phoneNumberError.setText(errorForm.getPhoneNumberError());
        emailErrorLabel.setText(errorForm.getEmailError());
    }

    @FXML
    private void save() {
        if (!error()) listener.listen(getForm());
    }

    private EditProfileForm getForm() {
        EditProfileForm form = new EditProfileForm(
                phoneNumberField.getText(),
                emailField.getText(),
                imageBase64
        );
        return form;
    }

    private boolean error() {
        phoneNumberError.setText("");
        emailErrorLabel.setText("");
        try {
            Integer.parseInt(phoneNumberField.getText());
        } catch (NumberFormatException e) {
            phoneNumberError.setText("لطفا عدد وارد کنید.");
            return true;
        }
        if (emailField.getText().equals("")) {
            emailErrorLabel.setText("لطفا ایمیل را وارد کنید.");
            return true;
        }
        return false;
    }

    private void setPicture(String imageBase64) {
        try {
            byte[] bytes = ImageUtil.decodeFromBase64(imageBase64);
            picture.setImage(new Image(new ByteArrayInputStream(bytes)));
            this.imageBase64 = imageBase64;
        } catch (Exception e) {
            String defaultPicture = this.imageBase64;
            if (this.imageBase64 == null) defaultPicture = new Config(Constants.CONFIG).getProperty(String.class, "defaultPicture");
            byte[] bytes = ImageUtil.decodeFromBase64(defaultPicture);
            picture.setImage(new Image(new ByteArrayInputStream(bytes)));
            pictureErrorLabel.setText("لطفا دوباره تلاش کنید.");
        }
    }

    @FXML
    private void choosePicture() {
        try {
            File file = fileChooser.showOpenDialog(new Stage());
            setPicture(ImageUtil.fileToString(file.getPath()));
        } catch (FileNotFoundException e) {
            pictureErrorLabel.setText("لطفا دوباره تلاش کنید.");
        }
    }

}
