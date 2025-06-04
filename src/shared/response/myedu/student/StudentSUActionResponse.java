package shared.response.myedu.student;

import shared.response.Response;
import shared.response.ResponseHandler;

public class StudentSUActionResponse extends Response {
    private String error = "";

    @Override
    public void handle(ResponseHandler handler) {
        handler.studentSUAction(this);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
