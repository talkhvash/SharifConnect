package shared.request.myedu.student;

import shared.model.edu.Department;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.myedu.student.RefreshStudentSUListResponse;

public class RefreshStudentSUListRequest extends Request {
    private final Department department;
    private final SelectUnitListSort sort;
    private final boolean tab;

    public RefreshStudentSUListRequest(Department department, SelectUnitListSort sort, boolean tab) {
        this.department = department;
        this.sort = sort;
        this.tab = tab;
    }

    @Override
    public RefreshStudentSUListResponse handle(RequestHandler handler) {
        return handler.refreshStudentSUList(department, sort, tab);
    }

    public Department getDepartment() {
        return department;
    }

    public SelectUnitListSort getSort() {
        return sort;
    }

    public boolean isTab() {
        return tab;
    }

}
