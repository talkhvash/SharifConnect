package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class AnswerMinorRequest extends Request {
    private final Integer id;
    private final boolean answer;

    public AnswerMinorRequest(Integer demanderId, boolean answer) {
        this.id = demanderId;
        this.answer = answer;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.answerMinor(id, answer);
        return new EmptyResponse();
    }

    public Integer getId() {
        return id;
    }

    public boolean isAnswer() {
        return answer;
    }

}
