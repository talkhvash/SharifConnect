package shared.request;

import shared.model.edu.enums.Degree;
import shared.model.edu.enums.Rand;
import shared.model.edu.enums.Year;

public class FilterStudentForm {
    private Degree degree;
    private Year year;
    private Rand rand;
    private Integer departmentId;

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Rand getRand() {
        return rand;
    }

    public void setRand(Rand rand) {
        this.rand = rand;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
