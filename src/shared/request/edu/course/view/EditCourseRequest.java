package shared.request.edu.course.view;

import shared.model.edu.course.Course;
import shared.request.Request;
import shared.request.RequestHandler;
import shared.response.edu.course.view.EditCourseResponse;

public class EditCourseRequest extends Request {
    private final Course course;
    private final Integer courseId;

    public EditCourseRequest(Course course, Integer courseId) {
        this.course = course;
        this.courseId = courseId;
    }

    @Override
    public EditCourseResponse handle(RequestHandler handler) {
        return handler.editCourse(courseId, course);
    }

}
