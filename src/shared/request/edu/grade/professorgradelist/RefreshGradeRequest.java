package shared.request.edu.grade.professorgradelist;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

public class RefreshGradeRequest extends Request {
    private final Integer id;
    private final Float number;
    private final String answer;
    private final boolean assignAnswer;

    public RefreshGradeRequest(Integer id, Float number, String answer, boolean assignAnswer) {
        this.id = id;
        this.number = number;
        this.answer = answer;
        this.assignAnswer = assignAnswer;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.refreshGrade(id, number, answer, assignAnswer);
        return new EmptyResponse();
    }

    public Integer getId() {
        return id;
    }

    public Float getNumber() {
        return number;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isAssignAnswer() {
        return assignAnswer;
    }
}
