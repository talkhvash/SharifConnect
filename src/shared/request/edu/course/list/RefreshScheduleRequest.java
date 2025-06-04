package shared.request.edu.course.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.list.RefreshScheduleResponse;

public class RefreshScheduleRequest extends Request {

    @Override
    public RefreshScheduleResponse handle(RequestHandler handler) {
        return handler.refreshSchedule();
    }
}
