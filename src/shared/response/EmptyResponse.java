package shared.response;

public class EmptyResponse extends Response{
    @Override
    public void handle(ResponseHandler handler) {
        // nothing
    }
}
