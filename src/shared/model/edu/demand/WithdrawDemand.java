package shared.model.edu.demand;

public class WithdrawDemand extends Demand{
    private int departmentId;
    private Boolean state;

    // getters and setters
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
