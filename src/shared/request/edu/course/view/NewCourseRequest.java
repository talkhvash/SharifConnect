package shared.request.edu.course.view;

import shared.model.edu.course.Course;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.view.NewCourseResponse;

public class NewCourseRequest extends Request {
    private final Course course;

    public NewCourseRequest(Course course) {
        this.course = course;
    }

    @Override
    public NewCourseResponse handle(RequestHandler handler) {
        return handler.newCourse(course);
    }

}
