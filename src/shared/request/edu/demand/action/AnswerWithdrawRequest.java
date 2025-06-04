package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class AnswerWithdrawRequest extends Request {
    private final Integer id;
    private final boolean answer;

    public AnswerWithdrawRequest(Integer requestId, boolean answer) {
        this.id = requestId;
        this.answer = answer;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.answerWithdraw(id,answer);
        return new EmptyResponse();
    }

    public Integer getId() {
        return id;
    }

    public boolean isAnswer() {
        return answer;
    }
}
