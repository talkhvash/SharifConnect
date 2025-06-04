package shared.request.edu.course.view;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.view.ViewEditCourseResponse;

public class ViewEditCourseRequest extends Request {
    private final Integer courseId;

    public ViewEditCourseRequest(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public ViewEditCourseResponse handle(RequestHandler handler) {
        return handler.viewEditCourse(courseId);
    }

}
