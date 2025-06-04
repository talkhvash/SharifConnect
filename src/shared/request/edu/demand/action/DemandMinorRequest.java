package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class DemandMinorRequest extends Request {
    private final String departmentName;

    public DemandMinorRequest(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.demandMinor(departmentName);
        return new EmptyResponse();
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
