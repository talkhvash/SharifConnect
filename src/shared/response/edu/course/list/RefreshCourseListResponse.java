package shared.response.edu.course.list;

import shared.model.edu.course.Course;
import shared.model.edu.user.User;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshCourseListResponse extends Response {
    private User viewer;
    private LinkedList<Course> list = new LinkedList<>();

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshCourseList(this);
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }

    public LinkedList<Course> getList() {
        return list;
    }

    public void setList(LinkedList<Course> list) {
        this.list = list;
    }

}
