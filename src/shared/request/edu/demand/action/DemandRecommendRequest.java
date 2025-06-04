package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class DemandRecommendRequest extends Request {
    private final String professorName;

    public DemandRecommendRequest(String professorName) {
        this.professorName = professorName;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.demandRecommend(professorName);
        return new EmptyResponse();
    }

    public String getProfessorName() {
        return professorName;
    }

}
