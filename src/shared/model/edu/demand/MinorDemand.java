package shared.model.edu.demand;

public class MinorDemand extends Demand {
    private int inDepartmentId;
    private int outDepartmentId;
    private Boolean inState;
    private Boolean outState;
    private Boolean state;

    // getters and setters

    public int getInDepartmentId() {
        return inDepartmentId;
    }

    public void setInDepartmentId(int inDepartmentId) {
        this.inDepartmentId = inDepartmentId;
    }

    public int getOutDepartmentId() {
        return outDepartmentId;
    }

    public void setOutDepartmentId(int outDepartmentId) {
        this.outDepartmentId = outDepartmentId;
    }

    public Boolean getInState() {
        return inState;
    }

    public void setInState(Boolean inState) {
        this.inState = inState;
    }

    public Boolean getOutState() {
        return outState;
    }

    public void setOutState(Boolean outState) {
        this.outState = outState;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}


