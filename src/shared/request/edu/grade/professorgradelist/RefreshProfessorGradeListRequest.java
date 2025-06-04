package shared.request.edu.grade.professorgradelist;

import shared.model.edu.course.Course;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.grade.list.RefreshProfessorGradeListResponse;

public class RefreshProfessorGradeListRequest extends Request {
    private final Course course;

    public RefreshProfessorGradeListRequest(Course course) {
        this.course = course;
    }

    @Override
    public RefreshProfessorGradeListResponse handle(RequestHandler handler) {
        return handler.refreshProfessorGradeList(course);
    }

    public Course getCourse() {
        return course;
    }
}
