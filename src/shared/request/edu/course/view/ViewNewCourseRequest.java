package shared.request.edu.course.view;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.view.ViewNewCourseResponse;

public class ViewNewCourseRequest extends Request {
    @Override
    public ViewNewCourseResponse handle(RequestHandler handler) {
        return handler.viewNewCourse();
    }
}
