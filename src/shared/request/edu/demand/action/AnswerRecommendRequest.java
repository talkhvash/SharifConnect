package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class AnswerRecommendRequest extends Request {
    private final Integer id;
    private final boolean answer;

    public AnswerRecommendRequest(Integer id, boolean answer) {
        this.id = id;
        this.answer = answer;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.answerRecommend(id, answer);
        return new EmptyResponse();
    }

    public Integer getId() {
        return id;
    }

    public boolean isAnswer() {
        return answer;
    }
}
