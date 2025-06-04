package shared.request.messenger;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.Response;
import shared.response.messenger.RefreshChatroomResponse;

public class RefreshChatroomRequest extends Request {

    @Override
    public RefreshChatroomResponse handle(RequestHandler handler) {
        return handler.refreshChatroom();
    }
}
