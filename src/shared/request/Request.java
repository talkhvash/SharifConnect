package shared.request;

import shared.response.Response;

public abstract class Request {
    public abstract Response handle(RequestHandler handler);

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
