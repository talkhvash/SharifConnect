package shared.request.myedu;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.myedu.ViewSUListResponse;

public class ViewSUListRequest extends Request {
    @Override
    public ViewSUListResponse handle(RequestHandler handler) {
        return handler.viewSUList();
    }
}
