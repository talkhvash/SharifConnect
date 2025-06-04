package shared.request.edu.main;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.main.RefreshMainMenuResponse;

public class RefreshMainMenuRequest extends Request {
    @Override
    public RefreshMainMenuResponse handle(RequestHandler handler) {
        return handler.refreshMainMenu();
    }
}
