package shared.response.myedu;

import shared.model.edu.Department;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class ViewSUListResponse extends Response {
    private Boolean result;
    // null -> vice chair
    // true -> student +
    // false -> student -
    private LinkedList<Department> departmentList;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewSUList(this);
    }

    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public LinkedList<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(LinkedList<Department> departmentList) {
        this.departmentList = departmentList;
    }

}
