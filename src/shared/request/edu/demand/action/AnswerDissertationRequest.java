package shared.request.edu.demand.action;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

import java.time.LocalDateTime;

public class AnswerDissertationRequest extends Request {
    private final Integer id;
    private final LocalDateTime time;

    public AnswerDissertationRequest(Integer id, LocalDateTime time) {
        this.id = id;
        this.time = time;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.answerDissertation(id, time);
        return new EmptyResponse();
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
