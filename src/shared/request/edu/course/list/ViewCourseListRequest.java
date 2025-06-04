package shared.request.edu.course.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.list.ViewCourseListResponse;

public class ViewCourseListRequest extends Request {
    @Override
    public ViewCourseListResponse handle(RequestHandler handler) {
        return handler.viewCourseList();
    }
}
