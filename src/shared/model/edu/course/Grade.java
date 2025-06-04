package shared.model.edu.course;

public class Grade {
    private int id;
    private int studentId;
    private int courseId;
    private Unit unit;

    private float number;
    private Boolean finalizedNumber;
    private String objection = "";
    private boolean finalizedObjection;
    private String answer = "";
    private boolean finalizedAnswer;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public Boolean getFinalizedNumber() {
        return finalizedNumber;
    }

    public void setFinalizedNumber(Boolean finalizedNumber) {
        this.finalizedNumber = finalizedNumber;
    }

    public String getObjection() {
        return objection;
    }

    public void setObjection(String objection) {
        this.objection = objection;
    }

    public boolean isFinalizedObjection() {
        return finalizedObjection;
    }

    public void setFinalizedObjection(boolean finalizedObjection) {
        this.finalizedObjection = finalizedObjection;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinalizedAnswer() {
        return finalizedAnswer;
    }

    public void setFinalizedAnswer(boolean finalizedAnswer) {
        this.finalizedAnswer = finalizedAnswer;
    }
}
