package shared.request.myedu.professor;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.EmptyResponse;
import shared.response.Response;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class SetStudentSUTimeRequest extends Request {
    private final LinkedList<Integer> studentIdList;
    private final LocalDateTime localDateTime;

    public SetStudentSUTimeRequest(LinkedList<Integer> studentIdList, LocalDateTime localDateTime) {
        this.studentIdList = studentIdList;
        this.localDateTime = localDateTime;
    }

    @Override
    public Response handle(RequestHandler handler) {
        handler.setStudentSUTime(studentIdList, localDateTime);
        return new EmptyResponse();
    }

    public LinkedList<Integer> getStudentIdList() {
        return studentIdList;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
