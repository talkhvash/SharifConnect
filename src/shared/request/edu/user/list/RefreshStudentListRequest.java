package shared.request.edu.user.list;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.Response;
import shared.response.edu.user.list.RefreshStudentListResponse;

public class RefreshStudentListRequest extends Request {
    private final Integer idPart;

    public RefreshStudentListRequest(Integer idPart) {
        this.idPart = idPart;
    }

    @Override
    public RefreshStudentListResponse handle(RequestHandler handler) {
        return handler.refreshStudentList(idPart);
    }

}
