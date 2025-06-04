package shared.request.edu.course.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.list.RefreshExamListResponse;

public class RefreshExamListRequest extends Request {
    @Override
    public RefreshExamListResponse handle(RequestHandler handler) {
        return handler.refreshExamList();
    }
}
