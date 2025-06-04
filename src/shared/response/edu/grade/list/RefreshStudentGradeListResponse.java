package shared.response.edu.grade.list;

import shared.model.edu.course.Grade;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshStudentGradeListResponse extends Response {
    private LinkedList<Grade> list;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshStudentGradeList(this);
    }

    public LinkedList<Grade> getList() {
        return list;
    }

    public void setList(LinkedList<Grade> list) {
        this.list = list;
    }


}
