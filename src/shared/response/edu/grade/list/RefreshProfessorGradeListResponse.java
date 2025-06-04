package shared.response.edu.grade.list;

import shared.model.edu.course.Grade;
import shared.response.Response;
import shared.response.ResponseHandler;

import java.util.LinkedList;

public class RefreshProfessorGradeListResponse extends Response {
    private LinkedList<Grade> list;
    private Boolean finalize;
    private Integer courseId;
    @Override
    public void handle(ResponseHandler handler) {
        handler.refreshProfessorGradeList(this);
    }

    public LinkedList<Grade> getList() {
        return list;
    }

    public void setList(LinkedList<Grade> list) {
        this.list = list;
    }

    public Boolean getFinalize() {
        return finalize;
    }

    public void setFinalize(Boolean finalize) {
        this.finalize = finalize;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
