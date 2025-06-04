package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class AnswerSelectUnitDemandRequest extends Request {
    private final boolean result;
    private final int id;

    public AnswerSelectUnitDemandRequest(boolean result, int id) {
        this.result = result;
        this.id = id;
    }

    @Override
    public EmptyResponse handle(RequestHandler handler) {
        handler.answerSelectUnit(id, result);
        return new EmptyResponse();
    }


}
