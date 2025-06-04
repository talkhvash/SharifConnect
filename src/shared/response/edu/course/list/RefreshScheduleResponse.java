package shared.response.edu.course.list;

import shared.model.edu.course.Course;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshScheduleResponse extends Response {
    private LinkedList<Course> list = new LinkedList<>();

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshSchedule(this);
    }

    public LinkedList<Course> getList() {
        return list;
    }

    public void setList(LinkedList<Course> list) {
        this.list = list;
    }
}
