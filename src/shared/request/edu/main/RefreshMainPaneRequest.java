package shared.request.edu.main;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.main.RefreshMainPaneResponse;

public class RefreshMainPaneRequest extends Request {
    @Override
    public RefreshMainPaneResponse handle(RequestHandler handler) {
        return handler.refreshMainPane();
    }
}
