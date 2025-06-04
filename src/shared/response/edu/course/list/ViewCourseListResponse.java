package shared.response.edu.course.list;

import shared.model.edu.Department;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class ViewCourseListResponse extends Response {
    private LinkedList<Department> depList = new LinkedList<>();

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewCourseList(this);
    }

    public LinkedList<Department> getDepList() {
        return depList;
    }

    public void setDepList(LinkedList<Department> depList) {
        this.depList = depList;
    }

}
