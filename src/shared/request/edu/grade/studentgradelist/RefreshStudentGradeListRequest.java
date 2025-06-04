package shared.request.edu.grade.studentgradelist;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.grade.list.RefreshStudentGradeListResponse;

public class RefreshStudentGradeListRequest extends Request {
    @Override
    public RefreshStudentGradeListResponse handle(RequestHandler handler) {
        return handler.refreshStudentGradeList();
    }
}
