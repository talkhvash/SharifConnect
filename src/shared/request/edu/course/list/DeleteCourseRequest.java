package shared.request.edu.course.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class DeleteCourseRequest extends Request {
    private final Integer courseId;

    public DeleteCourseRequest(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.deleteCourse(courseId);
        return new EmptyResponse();
    }

}
