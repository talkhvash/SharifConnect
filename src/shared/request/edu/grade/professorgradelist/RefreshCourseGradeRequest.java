package shared.request.edu.grade.professorgradelist;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class RefreshCourseGradeRequest extends Request {
    private final Integer courseId;
    private final Boolean finalize;

    public RefreshCourseGradeRequest(Integer courseId, Boolean finalize) {
        this.courseId = courseId;
        this.finalize = finalize;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.refreshCourseGrade(courseId, finalize);
        return new EmptyResponse();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Boolean getFinalize() {
        return finalize;
    }
}
