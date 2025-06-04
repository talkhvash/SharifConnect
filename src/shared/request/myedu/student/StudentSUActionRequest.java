package shared.request.myedu.student;

import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.myedu.student.StudentSUActionResponse;

public class StudentSUActionRequest extends Request {
    private final StudentSUActionType type;
    private final Integer courseId;
    private final Integer groupId;

    public StudentSUActionRequest(StudentSUActionType type, Integer courseId, Integer groupId) {
        this.type = type;
        this.courseId = courseId;
        this.groupId = groupId;
    }

    @Override
    public StudentSUActionResponse handle(RequestHandler handler) {
        return handler.studentSUActionResponse(type, courseId, groupId);
    }

    public StudentSUActionType getType() {
        return type;
    }

    public Integer getCourseId() {
        return courseId;
    }

}
