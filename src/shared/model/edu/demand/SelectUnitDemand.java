package shared.model.edu.demand;

public class SelectUnitDemand extends Demand {
    private Integer courseId;
    private Boolean state;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
