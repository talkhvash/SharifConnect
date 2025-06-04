package client.main.java.view.FXML.edu.user.view.professor;

import client.main.java.constants.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import client.main.java.db.ModelLoader;
import client.main.java.util.FormValidation;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shared.config.Config;
import shared.model.edu.Department;
import shared.model.edu.user.Professor;
import shared.model.edu.enums.ProfessorLevel;
import shared.response.edu.user.view.ViewProfessorErrorForm;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class ProfessorViewFXML {
    private final ProfessorViewListener listener = new ProfessorViewListener();

    @FXML
    TextField phoneNumberField,
            emailField,
            firstNameField,
            nationalNumberField,
            lastNameField,
            roomNumberField;
    @FXML
    Label firstNameErrorLabel,
            lastNameErrorLabel,
            nationalNumberErrorLabel,
            emailErrorLabel,
            roomNumberErrorLabel,
            phoneNumberErrorLabel,
            pictureErrorLabel,
            levelErrorLabel;
    @FXML
    Label departmentLabel,
            roleLabel;
    @FXML
    ChoiceBox<ProfessorLevel> levelBox;
    @FXML
    Button saveButton,
            pictureChooserButton;
    @FXML
    ImageView image;

    private String imageBase64;

    private final FileChooser fileChooser = new FileChooser();

    private final ObservableList<ProfessorLevel> levelItems = FXCollections.observableArrayList(
            ProfessorLevel.ASSISTANT_PROFESSOR,
            ProfessorLevel.PROFESSOR,
            ProfessorLevel.ASSOCIATE_PROFESSOR);

    private Professor professor;
    private Integer departmentId;

    public void initializeNewView(Professor viewer) {
        this.departmentId = viewer.getDepartmentId();
        Department department = ModelLoader.getLoader().loadDepartment(professor.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        levelBox.setItems(levelItems);

        newView();
    }


    public void initializeEditView(Professor professor) {
        this.professor = professor;

        Department department = ModelLoader.getLoader().loadDepartment(professor.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        levelBox.setItems(levelItems);

        editView();
    }

    private void newView() {
        roleLabel.setText("استاد");
        saveButton.setText("ثبت نام");
        setPicture(null);
    }

    private void editView() {
        setPicture(professor.getImageBase64());

        saveButton.setText("ذخیره");
        firstNameField.setText(professor.getFirstName());
        lastNameField.setText(professor.getLastName());
        nationalNumberField.setText(professor.getNationalNumber() + "");
        phoneNumberField.setText(professor.getPhoneNumber() + "");
        emailField.setText(professor.getEmail());
        roomNumberField.setText(professor.getRoomNumber() + "");
        levelBox.setValue(professor.getLevel());
        if (professor.isChairman()) roleLabel.setText("رئیس دانشکده");
        else if (professor.isViceChair()) roleLabel.setText("معاون آموزشی");
        else roleLabel.setText("استاد");
    }

    public void setError(ViewProfessorErrorForm error) {
        firstNameErrorLabel.setText(error.getFirstNameErrorLabel());
        lastNameErrorLabel.setText(error.getLastNameErrorLabel());
        nationalNumberErrorLabel.setText(error.getNationalNumberErrorLabel());
        emailErrorLabel.setText(error.getEmailErrorLabel());
        roomNumberErrorLabel.setText(error.getRoomNumberErrorLabel());
        phoneNumberErrorLabel.setText(error.getPhoneNumberErrorLabel());
        levelErrorLabel.setText(error.getLevelErrorLabel());
    }

    private boolean error() {
        firstNameErrorLabel.setText("");
        lastNameErrorLabel.setText("");
        nationalNumberErrorLabel.setText("");
        emailErrorLabel.setText("");
        roomNumberErrorLabel.setText("");
        phoneNumberErrorLabel.setText("");
        levelErrorLabel.setText("");

        FormValidation validation = new FormValidation();

        try {
            validation.isLong(nationalNumberField, nationalNumberErrorLabel);
            validation.isLong(phoneNumberField, phoneNumberErrorLabel);
            validation.isInteger(roomNumberField, roomNumberErrorLabel);
        } catch (NumberFormatException e) {
            return true;
        }

        return validation.isEmpty(firstNameField, firstNameErrorLabel)
                | validation.isEmpty(lastNameField, lastNameErrorLabel)
                | validation.isEmpty(emailField, emailErrorLabel)
                | validation.isEmpty(levelBox, levelErrorLabel);
    }

    private Professor getForm() {
        Professor professor = new Professor();
        professor.setImageBase64(imageBase64);
        professor.setFirstName(firstNameField.getText());
        professor.setLastName(lastNameField.getText());
        professor.setDepartmentId(departmentId);
        professor.setPhoneNumber(Long.parseLong(phoneNumberField.getText()));
        professor.setNationalNumber(Long.parseLong(nationalNumberField.getText()));
        professor.setEmail(emailField.getText());
        professor.setRoomNumber(Integer.parseInt(roomNumberField.getText()));
        professor.setLevel(professor.getLevel());
        return professor;
    }

    @FXML
    private void save() {
        if (professor == null) {
            if (!error()) listener.listen("new", getForm(), null);
        } else {
            if (!error()) listener.listen("edit", getForm(), professor.getId());
        }
    }

    private void setPicture(String imageBase64) {
        try {
            byte[] bytes = ImageUtil.decodeFromBase64(imageBase64);
            image.setImage(new Image(new ByteArrayInputStream(bytes)));
            this.imageBase64 = imageBase64;
        } catch (Exception e) {
            String defaultPicture = this.imageBase64;
            if (this.imageBase64 == null) defaultPicture = new Config(Constants.CONFIG).getProperty(String.class, "defaultPicture");
            byte[] bytes = ImageUtil.decodeFromBase64(defaultPicture);
            image.setImage(new Image(new ByteArrayInputStream(bytes)));
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
