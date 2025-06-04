package shared.request.edu.grade.vicechairgradelist;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.grade.list.RefreshViceChairGradeListResponse;

public class RefreshViceChairGradeListRequest extends Request {
    private final String studentName, professorName;
    private final Integer courseId, studentId;

    public RefreshViceChairGradeListRequest(String studentName, String professorName, Integer courseId, Integer studentId) {
        this.studentName = studentName;
        this.professorName = professorName;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @Override
    public RefreshViceChairGradeListResponse handle(RequestHandler handler) {
        return handler.refreshViceChairGradeList(studentName, professorName, courseId, studentId);
    }
}
