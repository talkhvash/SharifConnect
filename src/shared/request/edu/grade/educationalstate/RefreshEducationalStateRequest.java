package shared.request.edu.grade.educationalstate;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.grade.educationalstate.RefreshEducationalStateResponse;

public class RefreshEducationalStateRequest extends Request {
    private final String name;
    private final Integer id;

    public RefreshEducationalStateRequest(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public RefreshEducationalStateResponse handle(RequestHandler handler) {
        return handler.refreshEducationalState(name, id);
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

}
