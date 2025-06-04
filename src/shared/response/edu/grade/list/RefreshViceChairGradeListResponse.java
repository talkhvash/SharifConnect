package shared.response.edu.grade.list;

import shared.model.edu.course.Course;
import shared.model.edu.course.Grade;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.HashMap;
import java.util.LinkedList;

public class RefreshViceChairGradeListResponse extends Response {
    private boolean studentView;
    private LinkedList<Grade> list;
    private HashMap<Course, LinkedList<Grade>> map;

    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshViceChairGradeList(this);
    }

    public boolean isStudentView() {
        return studentView;
    }

    public void setStudentView(boolean studentView) {
        this.studentView = studentView;
    }

    public LinkedList<Grade> getList() {
        return list;
    }

    public void setList(LinkedList<Grade> list) {
        this.list = list;
    }

    public HashMap<Course, LinkedList<Grade>> getMap() {
        return map;
    }

    public void setMap(HashMap<Course, LinkedList<Grade>> map) {
        this.map = map;
    }
}
