package client.main.java.view.FXML.edu.user.profile.student;

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
import shared.model.edu.user.Student;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class StudentProfilePaneFXML {
    private StudentProfilePaneListener listener = new StudentProfilePaneListener();

    @FXML
    private Label nameLabel,
            idNumberLabel,
            departmentLabel,
            nationalCodeLabel,
            yearLabel,
            degreeLabel,
            gradeLabel,
            phoneNumberError,
            emailErrorLabel,
            pictureErrorLabel;
    @FXML
    private TextField phoneNumberField,
            emailField;
    @FXML
    private ImageView picture;
    @FXML
    private Button pictureChooserButton,
            changeButton;

    private final FileChooser fileChooser = new FileChooser();

    private String imageBase64;

    public void offlineView() {
        emailErrorLabel.setText("");
        phoneNumberError.setText("");
        pictureErrorLabel.setText("");
        pictureChooserButton.setDisable(true);
        changeButton.setDisable(true);
        phoneNumberField.setDisable(true);
        emailField.setDisable(true);
    }

    public void initialize(Student student) {
        nameLabel.setText(student.getName());
        idNumberLabel.setText(student.getId() + "");
        Department department = ModelLoader.getLoader().loadDepartment(student.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        nationalCodeLabel.setText(student.getNationalNumber() + "");
        yearLabel.setText(student.getYear() + "");
        degreeLabel.setText(student.getDegree().toString());
        gradeLabel.setText(student.getGrade() + "");
        phoneNumberField.setText(student.getPhoneNumber() + "");
        emailField.setText(student.getEmail());

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

    private boolean error() {
        phoneNumberError.setText("");
        emailErrorLabel.setText("");
        try {
            Long.parseLong(phoneNumberField.getText());
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

    private EditProfileForm getForm() {
        return new EditProfileForm(
                phoneNumberField.getText(),
                emailField.getText(),
                imageBase64
        );
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

