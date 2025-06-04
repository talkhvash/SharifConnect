package shared.model.edu.course;

import shared.model.edu.enums.Degree;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Course {
    private int id;
    private String name = "تعیین نشده";
    private int departmentId;
    private LinkedList<Integer> professorsId = new LinkedList<>();
    private LinkedList<Integer> assistantsId = new LinkedList<>();
    private LinkedList<Integer> studentsId = new LinkedList<>();

    private Degree degree;
    private Unit unit;
    private int number;
    private Integer hamniazNumber;
    private Integer pishniazNumber;
    private boolean maaref;

    private int group;
    private LinkedList<Integer> otherGroupId = new LinkedList<>();
    private boolean term;
    private int capacityCount;
    private int studentCount;
    private ScheduleTime scheduleTime;
    private LocalDateTime examDateTime;

    private LinkedList<Integer> gradesId = new LinkedList<>();
    private Boolean finalize;
    private int passStudentsCount;
    private int failStudentsCount;
    private float withFailedAverage;
    private float withoutFailedAverage;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public LinkedList<Integer> getProfessorsId() {
        return professorsId;
    }

    public void setProfessorsId(LinkedList<Integer> professorsId) {
        this.professorsId = professorsId;
    }

    public LinkedList<Integer> getAssistantsId() {
        return assistantsId;
    }

    public void setAssistantsId(LinkedList<Integer> assistantsId) {
        this.assistantsId = assistantsId;
    }

    public LinkedList<Integer> getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(LinkedList<Integer> studentsId) {
        this.studentsId = studentsId;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getHamniazNumber() {
        return hamniazNumber;
    }

    public void setHamniazNumber(Integer hamniazNumber) {
        this.hamniazNumber = hamniazNumber;
    }

    public Integer getPishniazNumber() {
        return pishniazNumber;
    }

    public void setPishniazNumber(Integer pishniazNumber) {
        this.pishniazNumber = pishniazNumber;
    }

    public boolean isMaaref() {
        return maaref;
    }

    public void setMaaref(boolean maaref) {
        this.maaref = maaref;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public LinkedList<Integer> getOtherGroupId() {
        return otherGroupId;
    }

    public void setOtherGroupId(LinkedList<Integer> otherGroupId) {
        this.otherGroupId = otherGroupId;
    }

    public boolean isTerm() {
        return term;
    }

    public void setTerm(boolean term) {
        this.term = term;
    }

    public int getCapacityCount() {
        return capacityCount;
    }

    public void setCapacityCount(int capacityCount) {
        this.capacityCount = capacityCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public ScheduleTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(ScheduleTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public LocalDateTime getExamDateTime() {
        return examDateTime;
    }

    public void setExamDateTime(LocalDateTime examDateTime) {
        this.examDateTime = examDateTime;
    }

    public LinkedList<Integer> getGradesId() {
        return gradesId;
    }

    public void setGradesId(LinkedList<Integer> gradesId) {
        this.gradesId = gradesId;
    }

    public Boolean getFinalize() {
        return finalize;
    }

    public void setFinalize(Boolean finalize) {
        this.finalize = finalize;
    }

    public int getPassStudentsCount() {
        return passStudentsCount;
    }

    public void setPassStudentsCount(int passStudentsCount) {
        this.passStudentsCount = passStudentsCount;
    }

    public int getFailStudentsCount() {
        return failStudentsCount;
    }

    public void setFailStudentsCount(int failStudentsCount) {
        this.failStudentsCount = failStudentsCount;
    }

    public float getWithFailedAverage() {
        return withFailedAverage;
    }

    public void setWithFailedAverage(float withFailedAverage) {
        this.withFailedAverage = withFailedAverage;
    }

    public float getWithoutFailedAverage() {
        return withoutFailedAverage;
    }

    public void setWithoutFailedAverage(float withoutFailedAverage) {
        this.withoutFailedAverage = withoutFailedAverage;
    }
}
