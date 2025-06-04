package shared.model.edu.user;



import shared.model.edu.enums.Degree;
import shared.model.edu.enums.Rand;
import shared.model.edu.enums.StudentState;
import shared.model.edu.enums.Year;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Student extends User {
    private Year year;
    private Degree degree;
    private StudentState state;

    private int passedUnit;
    private LinkedList<Integer> gradesId = new LinkedList<>();
    private float grade;

    private LocalDateTime selectUnitTime;
    private Rand rand;
    private boolean selectUnitPermission;
    private LinkedList<Integer> signedCourseId = new LinkedList<>();
    private LinkedList<Integer> passedCourseNumber = new LinkedList<>();

    private int supervisorId;

    private LinkedList<Integer> assistantCourseId = new LinkedList<>();

    private LinkedList<Integer> selectUnitDemandId = new LinkedList<>();
    private Integer dormDemandId,
            withdrawDemandId,
            dissertationDemandId,
            confirmationDemandId,
            recommendDemandId,
            minorDemandId;

    // getters and setters
    public LinkedList<Integer> getSelectUnitDemandId() {
        return selectUnitDemandId;
    }

    public void setSelectUnitDemandId(LinkedList<Integer> selectUnitDemandId) {
        this.selectUnitDemandId = selectUnitDemandId;
    }

    public LinkedList<Integer> getPassedCourseNumber() {
        return passedCourseNumber;
    }

    public void setPassedCourseNumber(LinkedList<Integer> passedCourseNumber) {
        this.passedCourseNumber = passedCourseNumber;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public StudentState getState() {
        return state;
    }

    public void setState(StudentState state) {
        this.state = state;
    }

    public int getPassedUnit() {
        return passedUnit;
    }

    public void setPassedUnit(int passedUnit) {
        this.passedUnit = passedUnit;
    }

    public LinkedList<Integer> getGradesId() {
        return gradesId;
    }

    public void setGradesId(LinkedList<Integer> gradesId) {
        this.gradesId = gradesId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public LocalDateTime getSelectUnitTime() {
        return selectUnitTime;
    }

    public void setSelectUnitTime(LocalDateTime selectUnitTime) {
        this.selectUnitTime = selectUnitTime;
    }

    public Rand getRand() {
        return rand;
    }

    public void setRand(Rand rand) {
        this.rand = rand;
    }

    public boolean isSelectUnitPermission() {
        return selectUnitPermission;
    }

    public void setSelectUnitPermission(boolean selectUnitPermission) {
        this.selectUnitPermission = selectUnitPermission;
    }

    public LinkedList<Integer> getSignedCourseId() {
        return signedCourseId;
    }

    public void setSignedCourseId(LinkedList<Integer> signedCourseId) {
        this.signedCourseId = signedCourseId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public LinkedList<Integer> getAssistantCourseId() {
        return assistantCourseId;
    }

    public void setAssistantCourseId(LinkedList<Integer> assistantCourseId) {
        this.assistantCourseId = assistantCourseId;
    }

    public Integer getDormDemandId() {
        return dormDemandId;
    }

    public void setDormDemandId(Integer dormDemandId) {
        this.dormDemandId = dormDemandId;
    }

    public Integer getWithdrawDemandId() {
        return withdrawDemandId;
    }

    public void setWithdrawDemandId(Integer withdrawDemandId) {
        this.withdrawDemandId = withdrawDemandId;
    }

    public Integer getDissertationDemandId() {
        return dissertationDemandId;
    }

    public void setDissertationDemandId(Integer dissertationDemandId) {
        this.dissertationDemandId = dissertationDemandId;
    }

    public Integer getConfirmationDemandId() {
        return confirmationDemandId;
    }

    public void setConfirmationDemandId(Integer confirmationDemandId) {
        this.confirmationDemandId = confirmationDemandId;
    }

    public Integer getRecommendDemandId() {
        return recommendDemandId;
    }

    public void setRecommendDemandId(Integer recommendDemandId) {
        this.recommendDemandId = recommendDemandId;
    }

    public Integer getMinorDemandId() {
        return minorDemandId;
    }

    public void setMinorDemandId(Integer minorDemandId) {
        this.minorDemandId = minorDemandId;
    }
}
