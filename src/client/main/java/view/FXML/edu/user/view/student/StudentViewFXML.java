package client.main.java.view.FXML.edu.user.view.student;

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
import shared.model.edu.enums.Degree;
import shared.model.edu.Department;
import shared.model.edu.enums.Rand;
import shared.model.edu.enums.Year;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;
import shared.response.edu.user.view.ViewStudentErrorForm;
import shared.util.ImageUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class StudentViewFXML {
    private final StudentViewListener listener = new StudentViewListener();

    @FXML
    private TextField phoneNumberField,
            emailField,
            firstNameField,
            nationalNumberField,
            lastNameField,
            supervisorField;
    @FXML
    private Label firstNameError,
            lastNameError,
            nationalNumberError,
            emailError,
            yearError,
            phoneNumberError,
            pictureErrorLabel,
            degreeError,
            supervisorError,
            randErrorLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private ChoiceBox<Rand> randBox;
    @FXML
    private ChoiceBox<Degree> degreeBox;
    @FXML
    private ChoiceBox<Year> yearBox;
    @FXML
    private Button saveButton,
            pictureChooserButton;
    @FXML
    ImageView image;

    private final FileChooser fileChooser = new FileChooser();

    private final ObservableList<Rand> randItems = FXCollections.observableArrayList(
            Rand.RAND_1, Rand.RAND_2, Rand.RAND_3, Rand.RAND_4
    );

    private final ObservableList<Year> yearItems = FXCollections.observableArrayList(
            Year.YEAR_1401, Year.YEAR_1400, Year.YEAR_1399, Year.YEAR_1398, Year.YEAR_1397, Year.YEAR_1396, Year.YEAR_1395,
            Year.YEAR_1394, Year.YEAR_1393, Year.YEAR_1392, Year.YEAR_1391, Year.YEAR_1390
    );

    private final ObservableList<Degree> degreeItems = FXCollections.observableArrayList(
            Degree.DOCTORATE,
            Degree.POSTGRADUATE,
            Degree.UNDERGRADUATE);

    private Student student;
    private Integer departmentId;

    private String imageBase64;

    public void initializeNewView(Professor viewer) {
        this.departmentId = viewer.getDepartmentId();
        Department department = ModelLoader.getLoader().loadDepartment(student.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        degreeBox.setItems(degreeItems);
        yearBox.setItems(yearItems);
        randBox.setItems(randItems);
        newView();
    }

    public void initializeEditView(Student student) {
        this.student = student;

        Department department = ModelLoader.getLoader().loadDepartment(student.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        degreeBox.setItems(degreeItems);
        yearBox.setItems(yearItems);
        randBox.setItems(randItems);
        editView();
    }

    private void newView() {
        saveButton.setText("ثبت نام");
        setPicture(null);
    }

    private void editView() {
        saveButton.setText("ذخیره");

        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        nationalNumberField.setText(student.getNationalNumber() + "");
        phoneNumberField.setText(student.getPhoneNumber() + "");
        emailField.setText(student.getEmail());
        yearBox.setValue(student.getYear());
        randBox.setValue(student.getRand());
        degreeBox.setValue(student.getDegree());
        supervisorField.setText(student.getSupervisorId() + "");
        imageBase64 = student.getImageBase64();
        setPicture(imageBase64);
    }

    public void setError(ViewStudentErrorForm error) {
        firstNameError.setText(error.getFirstNameError());
        lastNameError.setText(error.getLastNameError());
        nationalNumberError.setText(error.getNationalNumberError());
        emailError.setText(error.getEmailError());
        yearError.setText(error.getYearError());
        phoneNumberError.setText(error.getPhoneNumberError());
        degreeError.setText(error.getDegreeError());
        supervisorError.setText(error.getSupervisorError());
        randErrorLabel.setText(error.getRandError());
    }

    private boolean error() {
        firstNameError.setText("");
        lastNameError.setText("");
        nationalNumberError.setText("");
        emailError.setText("");
        yearError.setText("");
        phoneNumberError.setText("");
        degreeError.setText("");
        supervisorError.setText("");
        randErrorLabel.setText("");

        FormValidation validation = new FormValidation();

        try {
            validation.isLong(nationalNumberField, nationalNumberError);
            validation.isLong(phoneNumberField, phoneNumberError);
            validation.isInteger(supervisorField, supervisorError);
        } catch (NumberFormatException e) {
            return true;
        }

        return validation.isEmpty(firstNameField, firstNameError)
                | validation.isEmpty(lastNameField, lastNameError)
                | validation.isEmpty(emailField, emailError)
                | validation.isEmpty(degreeBox, degreeError)
                | validation.isEmpty(yearBox, yearError)
                | validation.isEmpty(randBox, randErrorLabel );

    }

    private Student getForm() {
        Student student = new Student();
        student.setImageBase64(imageBase64);
        student.setFirstName(firstNameField.getText());
        student.setDepartmentId(departmentId);
        student.setLastName(lastNameField.getText());
        student.setNationalNumber(Long.parseLong(nationalNumberField.getText()));
        student.setYear(yearBox.getValue());
        student.setDegree(degreeBox.getValue());
        student.setEmail(emailField.getText());
        student.setRand(randBox.getValue());
        student.setSupervisorId(Integer.parseInt(supervisorField.getText()));
        return student;
    }

    @FXML
    private void save() {
        if (student == null) {
            if (!error()) listener.listen("new", getForm(), null);
        } else {
            if (!error()) listener.listen("edit", getForm(), student.getId());
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
