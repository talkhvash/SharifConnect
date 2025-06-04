package shared.request.edu.course.list;

import shared.request.FilterCourseForm;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.list.RefreshCourseListResponse;

public class RefreshCourseListRequest extends Request {
    private final FilterCourseForm filter;

    public RefreshCourseListRequest(FilterCourseForm filter) {
        this.filter = filter;
    }

    @Override
    public RefreshCourseListResponse handle(RequestHandler handler) {
        return handler.refreshCourseList(filter);
    }

}
