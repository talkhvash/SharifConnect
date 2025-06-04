package shared.request.edu.user.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.user.list.RefreshProfessorListResponse;

public class RefreshProfessorListRequest extends Request {
    @Override
    public RefreshProfessorListResponse handle(RequestHandler handler) {
        return handler.refreshProfessorList();
    }
}
