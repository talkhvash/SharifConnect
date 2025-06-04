package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class DemandSelectUnitRequest extends Request {
    private final Integer courseId;

    public DemandSelectUnitRequest(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public EmptyResponse handle(RequestHandler handler) {
        handler.demandSelectUnit(courseId);
        return new EmptyResponse();
    }
}
