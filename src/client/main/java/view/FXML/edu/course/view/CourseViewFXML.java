package client.main.java.view.FXML.edu.course.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import client.main.java.db.ModelLoader;
import client.main.java.util.FormValidation;
import shared.response.edu.course.view.CourseViewError;
import shared.model.edu.course.Course;
import shared.model.edu.enums.Degree;
import shared.model.edu.Department;
import shared.model.edu.course.ScheduleTime;
import shared.model.edu.course.Unit;
import shared.model.edu.user.Professor;
import shared.model.edu.user.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class CourseViewFXML {
    private final CourseViewListener listener = new CourseViewListener();
    @FXML
    private TextField nameField,
            numberField,
            pishniazField,
            hamniazField,
            capacityField,
            professorIdField,
            assistantIdField,
            examHourField,
            examMinuteField,
            startHourField,
            startMinuteField,
            endHourField,
            endMinuteField,
            groupField;
    @FXML
    private DatePicker examDate;
    @FXML
    private ChoiceBox<Unit> unitBox;
    @FXML
    private ChoiceBox<Degree> degreeBox;
    @FXML
    private CheckMenuItem saturday,
            sunday,
            monday,
            tuesday,
            thursday,
            wednesday;
    @FXML
    private Label departmentLabel,
            nameErrorLabel,
            numberErrorLabel,
            pishniazErrorLabel,
            hamniazErrorLabel,
            unitErrorLabel,
            degreeErrorLabel,
            capacityErrorLabel,
            examDateErrorLabel,
            examTimeErrorLabel,
            daysErrorLabel,
            startTimeErrorLabel,
            endTimeErrorLabel,
            professorErrorLabel,
            assistantErrorLabel,
            groupErrorLabel;
    @FXML
    private VBox professorBox,
            assistantBox;
    @FXML
    private Button saveButton;

    private final ObservableList<Unit> unitBoxItems = FXCollections.observableArrayList(
            Unit.ONE,
            Unit.TOW,
            Unit.THREE,
            Unit.FOUR);
    private final ObservableList<Degree> degreeBoxItems = FXCollections.observableArrayList(
            Degree.UNDERGRADUATE,
            Degree.POSTGRADUATE,
            Degree.DOCTORATE);

    private Course course;

    private LinkedList<Integer> professorsId;
    private LinkedList<Integer> assistantsId;

    private int departmentId;

    public void initializeNewView(Professor viewer) {
        Department department = ModelLoader.getLoader().loadDepartment(viewer.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        departmentId = viewer.getDepartmentId();
        unitBox.setItems(unitBoxItems);
        degreeBox.setItems(degreeBoxItems);

        newView();
    }

    public void initializeEditView(Course course) {
        this.course = course;
        Department department = ModelLoader.getLoader().loadDepartment(course.getDepartmentId());
        if (department != null) departmentLabel.setText(department.getName());
        departmentId = course.getDepartmentId();
        unitBox.setItems(unitBoxItems);
        degreeBox.setItems(degreeBoxItems);

        editView();
    }

    private void newView() {
        saveButton.setText("ثبت نام");
        professorsId = new LinkedList<>();
        assistantsId = new LinkedList<>();
    }

    private void editView() {
        saveButton.setText("ذخیره");
        nameField.setText(course.getName());
        numberField.setText(course.getNumber() + "");
        pishniazField.setText(course.getPishniazNumber() + "");
        hamniazField.setText(course.getHamniazNumber() + "");
        unitBox.setValue(course.getUnit());
        degreeBox.setValue(course.getDegree());
        capacityField.setText(course.getCapacityCount() + "");
        LocalDate date = LocalDate.from(course.getExamDateTime());
        examDate.setValue(date);
        examHourField.setText(course.getExamDateTime().getHour() + "");
        examMinuteField.setText(course.getExamDateTime().getMinute() + "");
        boolean[] days = course.getScheduleTime().getDays();
        saturday.setSelected(days[0]);
        sunday.setSelected(days[1]);
        monday.setSelected(days[2]);
        tuesday.setSelected(days[3]);
        wednesday.setSelected(days[4]);
        thursday.setSelected(days[5]);
        startHourField.setText(course.getScheduleTime().getStart().getHour() + "");
        startMinuteField.setText(course.getScheduleTime().getStart().getMinute() + "");
        endHourField.setText(course.getScheduleTime().getEnd().getHour() + "");
        endMinuteField.setText(course.getScheduleTime().getEnd().getMinute() + "");
        professorsId = course.getProfessorsId();
        assistantsId = course.getAssistantsId();
        groupField.setText(course.getGroup() + "");
        setBoxes();
    }

    private void setBoxes() {
        assistantErrorLabel.setText("");
        professorErrorLabel.setText("");
        professorBox.getChildren().clear();
        assistantBox.getChildren().clear();
        for (Integer item : professorsId) {
            Professor professor = (Professor) ModelLoader.getLoader().loadUser(item);
            professorBox.getChildren().add(new Label(professor.getName()));
        }
        for (Integer item : assistantsId) {
            Student student = (Student) ModelLoader.getLoader().loadUser(item);
            assistantBox.getChildren().add(new Label(student.getName()));
        }
    }

    public void setError(CourseViewError error) {
        capacityErrorLabel.setText(error.getCapacityError());
        daysErrorLabel.setText(error.getDaysError());
        endTimeErrorLabel.setText(error.getEndTimeError());
        nameErrorLabel.setText(error.getNameError());
        degreeErrorLabel.setText(error.getDegreeError());
        unitErrorLabel.setText(error.getUnitError());
        numberErrorLabel.setText(error.getNumberError());
        pishniazErrorLabel.setText(error.getPishniazError());
        hamniazErrorLabel.setText(error.getHamniazError());
        examDateErrorLabel.setText(error.getExamDateError());
        examTimeErrorLabel.setText(error.getExamTimeError());
        startTimeErrorLabel.setText(error.getStartTimeError());
        assistantErrorLabel.setText(error.getAssistantError());
        professorErrorLabel.setText(error.getProfessorError());
        groupErrorLabel.setText(error.getGroupErrorLabel());
    }

    private boolean error() {
        capacityErrorLabel.setText("");
        daysErrorLabel.setText("");
        endTimeErrorLabel.setText("");
        nameErrorLabel.setText("");
        degreeErrorLabel.setText("");
        unitErrorLabel.setText("");
        numberErrorLabel.setText("");
        pishniazErrorLabel.setText("");
        hamniazErrorLabel.setText("");
        examDateErrorLabel.setText("");
        examTimeErrorLabel.setText("");
        startTimeErrorLabel.setText("");
        assistantErrorLabel.setText("");
        professorErrorLabel.setText("");
        groupErrorLabel.setText("");

        FormValidation validation = new FormValidation();

        try {
            validation.isInteger(numberField, numberErrorLabel);
            if (!pishniazField.getText().equals("")) validation.isInteger(pishniazField, pishniazErrorLabel);
            if (!hamniazField.getText().equals("")) validation.isInteger(hamniazField, hamniazErrorLabel);
            validation.isInteger(capacityField, capacityErrorLabel);
            validation.isInteger(examHourField, examTimeErrorLabel);
            validation.isInteger(examMinuteField, examTimeErrorLabel);
            validation.isInteger(startHourField, startTimeErrorLabel);
            validation.isInteger(startMinuteField, startTimeErrorLabel);
            validation.isInteger(endHourField, endTimeErrorLabel);
            validation.isInteger(endMinuteField, endTimeErrorLabel);
            validation.isInteger(groupField, groupErrorLabel);
        } catch (NumberFormatException e) {
            return true;
        }

        if (!(saturday.isSelected() || sunday.isSelected() || monday.isSelected() || tuesday.isSelected()
                || thursday.isSelected() || wednesday.isSelected() || thursday.isSelected())) {
            daysErrorLabel.setText("حداقل یک روز باید انتخاب شده باشد.");
            return true;
        }

        return validation.isEmpty(nameField, nameErrorLabel)
                || validation.isEmpty(unitBox, unitErrorLabel)
                || validation.isEmpty(degreeBox, degreeErrorLabel)
                || validation.isEmpty(examDate, examDateErrorLabel);
    }

    private Course getForm() {
        Course course = new Course();
        course.setGroup(Integer.parseInt(groupField.getText()));
        course.setDepartmentId(departmentId);
        course.setName(nameField.getText());
        course.setNumber(Integer.parseInt(numberField.getText()));
        course.setUnit(unitBox.getValue());
        course.setDegree(degreeBox.getValue());
        try {
            course.setHamniazNumber(Integer.parseInt(hamniazField.getText()));
        } catch (Exception ignored) {
        }
        try {
            course.setPishniazNumber(Integer.parseInt(pishniazField.getText()));
        } catch (Exception ignored) {
        }
        course.setCapacityCount(Integer.parseInt(capacityField.getText()));
        LocalDate date = examDate.getValue();
        LocalTime time = LocalTime.of(
                Integer.parseInt(examHourField.getText()),
                Integer.parseInt(examMinuteField.getText()));
        course.setExamDateTime(LocalDateTime.of(date, time));
        ScheduleTime scheduleTime = new ScheduleTime();
        boolean[] days = new boolean[]{
                saturday.isSelected(), sunday.isSelected(), monday.isSelected(), tuesday.isSelected(),
                thursday.isSelected(), wednesday.isSelected()};
        scheduleTime.setDays(days);
        LocalTime start = LocalTime.of(
                Integer.parseInt(startHourField.getText()),
                Integer.parseInt(startMinuteField.getText()));
        scheduleTime.setStart(start);
        LocalTime end = LocalTime.of(
                Integer.parseInt(endHourField.getText()),
                Integer.parseInt(endMinuteField.getText()));
        scheduleTime.setStart(end);
        course.setScheduleTime(scheduleTime);
        course.setProfessorsId(professorsId);
        course.setAssistantsId(assistantsId);
        return course;
    }

    @FXML
    private void save() {
        if (course == null) {
            if (!error()) listener.listen("new", getForm(), null);
        } else {
            if (!error()) listener.listen("edit", getForm(), course.getId());
        }
    }

    @FXML
    private void addProfessor() {
        try {
            Integer id = Integer.parseInt(professorIdField.getText());
            Professor professor = (Professor) ModelLoader.getLoader().loadUser(id);
            if (professor == null) {
                professorErrorLabel.setText("شماره استادی موجود نیست.");
            } else {
                professorsId.add(id);
                setBoxes();
            }
        } catch (NumberFormatException e) {
            professorErrorLabel.setText("لطفا عدد وارد کنید.");
        }
    }

    @FXML
    private void removeProfessor() {
        try {
            Integer id = Integer.parseInt(professorIdField.getText());
            if (professorsId.remove(id)) {
                setBoxes();
            } else {
                professorErrorLabel.setText("شماره استادی موجود نیست.");
            }
        } catch (NumberFormatException e) {
            professorErrorLabel.setText("لطفا عدد وارد کنید.");
        }
    }

    @FXML
    private void addAssistant() {
        try {
            Integer id = Integer.parseInt(assistantIdField.getText());
            Student student = (Student) ModelLoader.getLoader().loadUser(id);
            if (student == null) {
                assistantErrorLabel.setText("شماره دانشجویی موجود نیست.");
            } else {
                assistantsId.add(id);
                setBoxes();
            }
        } catch (NumberFormatException e) {
            assistantErrorLabel.setText("لطفا عدد وارد کنید.");
        }
    }

    @FXML
    private void removeAssistant() {
        try {
            Integer id = Integer.parseInt(assistantIdField.getText());
            if (assistantsId.remove(id)) {
                setBoxes();
            } else {
                assistantErrorLabel.setText("شماره استادی موجود نیست.");
            }
        } catch (NumberFormatException e) {
            assistantErrorLabel.setText("لطفا عدد وارد کنید.");
        }
    }

}
