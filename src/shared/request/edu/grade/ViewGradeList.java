package shared.request.edu.grade;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.grade.list.ViewGradeListResponse;

public class ViewGradeList extends Request {

    @Override
    public ViewGradeListResponse handle(RequestHandler handler) {
        return handler.viewGradeList();
    }

}
