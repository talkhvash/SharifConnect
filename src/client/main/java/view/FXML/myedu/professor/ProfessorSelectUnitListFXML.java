package client.main.java.view.FXML.myedu.professor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import client.main.java.util.FormValidation;
import shared.model.edu.enums.Degree;
import shared.model.edu.enums.Rand;
import shared.model.edu.enums.Year;
import shared.model.edu.user.Student;
import shared.request.FilterStudentForm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class ProfessorSelectUnitListFXML {
    private final ProfessorSelectUnitListListener listener = new ProfessorSelectUnitListListener();

    @FXML
    private ChoiceBox<Degree> degreeBox;
    @FXML
    private ChoiceBox<Year> yearBox;
    @FXML
    private ChoiceBox<Rand> randBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField hourField,
            minuteField;
    @FXML
    private VBox box;
    @FXML
    private Label dateErrorLabel,
            timeErrorLabel;

    private LinkedList<Integer> studentIdList;

    private final ObservableList<Degree> degreeBoxItem = FXCollections.observableArrayList(
            null,
            Degree.UNDERGRADUATE, Degree.POSTGRADUATE, Degree.DOCTORATE
    );
    private final ObservableList<Year> yearBoxItem = FXCollections.observableArrayList(
            null,
            Year.YEAR_1390, Year.YEAR_1391, Year.YEAR_1392, Year.YEAR_1393, Year.YEAR_1394, Year.YEAR_1395,
            Year.YEAR_1396, Year.YEAR_1397, Year.YEAR_1398, Year.YEAR_1399, Year.YEAR_1400, Year.YEAR_1401
    );
    private final ObservableList<Rand> randBoxItem = FXCollections.observableArrayList(
            null,
            Rand.RAND_1, Rand.RAND_2, Rand.RAND_3, Rand.RAND_4
    );

    public void initialize() {
        degreeBox.setItems(degreeBoxItem);
        yearBox.setItems(yearBoxItem);
        randBox.setItems(randBoxItem);
        degreeBox.setOnAction(event -> refreshRequest());
        yearBox.setOnAction(event -> refreshRequest());
        randBox.setOnAction(event -> refreshRequest());
    }

    public void refresh(LinkedList<Student> list) {
        studentIdList = new LinkedList<>();

        box.getChildren().clear();
        for (Student item : list) {
            studentIdList.add(item.getId());
            ProfessorSelectUnitListItem temp = new ProfessorSelectUnitListItem();
            temp.getFXML().refresh(item);
            box.getChildren().add(temp.getHBox());
        }

    }

    private LocalDateTime getTime() {
        LocalDate date = datePicker.getValue();
        LocalTime time = LocalTime.of(Integer.parseInt(hourField.getText()), Integer.parseInt(minuteField.getText()));
        return LocalDateTime.of(date, time);
    }

    @FXML
    private void set() {
        if (!error()) listener.listen(studentIdList, getTime());
    }

    private void refreshRequest() {
        listener.listen(getFiler());
    }

    private FilterStudentForm getFiler() {
        FilterStudentForm form = new FilterStudentForm();
        form.setDegree(degreeBox.getValue());
        form.setRand(randBox.getValue());
        form.setYear(yearBox.getValue());
        return form;
    }

    private boolean error() {
        FormValidation validation = new FormValidation();
        try {
            validation.isInteger(hourField, timeErrorLabel);
            validation.isInteger(minuteField, timeErrorLabel);
        } catch (NumberFormatException e) {
            return true;
        }
        return validation.isEmpty(datePicker, dateErrorLabel);
    }

}
