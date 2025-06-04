package shared.response.edu.grade.list;

import shared.model.edu.course.Course;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class ViewGradeListResponse extends Response {
    private Boolean user;
    // null student # false professor # true vice chair
    private LinkedList<Course> list;

    @Override
    public void handle(ResponseHandler handler) {
        handler.viewGradeList(this);
    }

    public Boolean getUser() {
        return user;
    }

    public void setUser(Boolean user) {
        this.user = user;
    }

    public LinkedList<Course> getList() {
        return list;
    }

    public void setList(LinkedList<Course> list) {
        this.list = list;
    }
}
